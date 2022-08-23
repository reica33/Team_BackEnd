package com.example.teamproject.service;

import com.example.teamproject.model.Notice;
import com.example.teamproject.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServie
 * author : naraekwon
 * date : 2022/06/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/07         naraekwon          최초 생성
 */
public interface NoticeService {
    //      id로 회원 조회 메소드(결과 1건)
    Optional<Notice> findById(Long id);

    //    모든 회원 조회 메소드 (결과 여러건)
//    List<Faqboard> findAll();

    // TODO: findAll() 수정, findByEmailContaining
    //    회원 전체 조회하는 메소드
    List<Notice> findAll(Criteria criteria);

    List<Notice> findByTitleContaining(Criteria criteria);

    //    회원 저장, 수정 메소드(insert / update)
    Optional<Notice> save (Notice faqboard);

    //    id로 회원 삭제 메소드
    boolean deleteById(Long id);

    //    전체 회원 삭제 메소드
    boolean deleteAll();
}
