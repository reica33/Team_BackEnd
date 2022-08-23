package com.example.teamproject.service;

import com.example.teamproject.dao.FileDBDao;
import com.example.teamproject.model.FileDB;
import com.example.teamproject.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class FileDBService {

  @Autowired
  private FileDBDao fileDBDao;


  Logger logger = LoggerFactory.getLogger(this.getClass());

  public int store( String title, String content, MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    todo: filedb 수정 (title, content)
    FileDB FileDB = new FileDB(fileName, title, content, file.getContentType(), file.getBytes());


    logger.info(" fileName {} " , fileName);
    logger.info(" title {} " , title);
    logger.info(" content {} " , content);

    return fileDBDao.save(FileDB);
  }

  public FileDB  getFile(String id) {
    return fileDBDao.findById(id);
  }

//  todo:
  public Stream<FileDB> getAllFiles(Criteria criteria) {

//    todo:
    int totalCount = fileDBDao.selectTotalCount();

//		Tutorials 총 건수
    criteria.setTotalItems(totalCount);
    //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
    criteria.setTotalPages(totalCount/criteria.getSize());
    //    list -> stream 객체로 변환
    Stream<FileDB> resFileDB = fileDBDao.findAll(criteria).stream();

    return resFileDB;
  }

// TODO 추가
  public boolean deleteById(Long id) {
    int queryResult = 0;

    queryResult = fileDBDao.deleteById(id);

    return (queryResult >= 1) ? true : false;
  }

//  TODO findAll , findByTitleContaining 추가
  public List<FileDB> findAll(Criteria criteria) {
    return fileDBDao.findAll(criteria);
  }


//  public List<FileDB> findByTitleContaining(Criteria criteria) {
////        빈값으로 초기화
//    List<FileDB> fileDBS = Collections.emptyList();
//
////      title이  null인지 체크(null -> '')
////    Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());
////        테이블의 총 데이터 건수
////    int totalCount = fileDBDao.selectTotalCount(optionalCriteria.orElse(""));
//    int totalCount = fileDBDao.selectTotalCount();
//
////        title 이 없으면 전체검색 -> criteria: 페이징 처리 클래스 객체
//    criteria.setTotalItems(totalCount);
////       총 페이지 개수 =  테이블의 총 건수/페이지당 출력할 데이터 개수(totalCount/size)
//    criteria.setTotalPages(totalCount/ criteria.getSize());
////        전체검색 : 전체검색에 타이틀없으므로 null 에러 안남 (title이 null이면 findAll호출 )
//    if(criteria.getTitle() == null){
//      fileDBS = fileDBDao.findAll(criteria);
////        title 이 있으면 제목검색(title이 null이 아님)
//    }else{
//      fileDBS = fileDBDao.findByTitleContaining(criteria);
//    }
//
//    return fileDBS;
//  }
}
