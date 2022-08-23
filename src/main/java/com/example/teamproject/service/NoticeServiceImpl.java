package com.example.teamproject.service;

import com.example.teamproject.dao.NoticeDao;
import com.example.teamproject.model.Notice;
import com.example.teamproject.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : naraekwon
 * date : 2022/06/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/07         naraekwon          최초 생성
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    //    생성된 객체를 받아오는 어노테이션
    @Autowired
    private NoticeDao noticeDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
//    id로 회원조회하는 메소드
    public Optional<Notice> findById(Long id) {
        return noticeDao.findById(id);
    }

    // TODO: findAll() 수정, findByEmailContaining
    @Override
//    회원 전체 조회하는 메소드
    public List<Notice> findAll(Criteria criteria) {
        return noticeDao.findAll(criteria);
    }

    @Override
    public List<Notice> findByTitleContaining(Criteria criteria) {
//        빈값으로 초기화
        List<Notice> notices = Collections.emptyList();

//      title이  null인지 체크(null -> '')
        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());
//        테이블의 총 데이터 건수
        int totalCount = noticeDao.selectTotalCount(optionalCriteria.orElse(""));

//        title 이 없으면 전체검색 -> criteria: 페이징 처리 클래스 객체
        criteria.setTotalItems(totalCount);
//       총 페이지 개수 =  테이블의 총 건수/페이지당 출력할 데이터 개수(totalCount/size)
        criteria.setTotalPages(totalCount/ criteria.getSize());
//        전체검색 : 전체검색에 타이틀없으므로 null 에러 안남 (title이 null이면 findAll호출 )
        if(criteria.getTitle() == null){
            notices = noticeDao.findAll(criteria);
//        title 이 있으면 제목검색(title이 null이 아님)
        }else{
            notices = noticeDao.findByTitleContaining(criteria);
        }

        return notices;
    }
    @Override
//    새 회원생성 또는 수정하는 메소드(insert / update)
    public Optional<Notice> save(Notice notice) {
        long seqId =0;

//        매개변수 tutorial 안의 값을 로그로 출력
        logger.info("faqboard{}", notice);

        if(notice.getId()==null){
//            id 값이 없으면 insert 문 실행(새글 저장)
            noticeDao.insertNotice(notice);
        }else{
//            id 값이 있으면 update 문 실행(수정)
            noticeDao.updateNotice(notice);
        }
//        insert 문 후에는 customer의 id 속성에 값이 저장된(<selectkey>)
        seqId = notice.getId();
        logger.info("seqID{}", seqId);
        return noticeDao.findById(seqId);
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult =0;

        queryResult = noticeDao.deleteNotice(id);

        return (queryResult>=1)?true:false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult =0;

        queryResult = noticeDao.deleteAll();

        return (queryResult>=1)?true:false;
    }

}
