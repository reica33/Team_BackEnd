package com.example.teamproject.service;

import com.example.teamproject.dao.UserDao;
import com.example.teamproject.model.Profile;
import com.example.teamproject.paging.Criteria;
import com.example.teamproject.dao.ProfileDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ProfileService {


  @Autowired
  UserDao userDao; //객체정의(null)


  @Autowired
  private ProfileDao profileDao;

  Logger logger = LoggerFactory.getLogger(this.getClass());



//
//  public List<Profile> findByUsernameContaining(Criteria criteria) {
//
//    //빈값으로 초기화
//    List<Profile> Profiles = Collections.emptyList();
//
//    //Title이 Null인지 체크 => ""
//    Optional<String> optionalCriteria = Optional.ofNullable(criteria.getUsername());
//
//    //테이블의 총 데이터 건수
//    int totalCount = profileDao.selectTotalCount();
//
//    //criteria : 페이징 처리 클래스 객체
//    criteria.setTotalItems(totalCount);
//    // 총 페이지 수 : 테이블의 총 건수(totalCount) / 페이지당 출력할 데이터 개수(size)
//    criteria.setTotalPages(totalCount / criteria.getSize());
//
//    if(criteria.getUsername() == null) {
//      //title이 없으면 전체 검색을 함
//      Profiles = profileDao.findAll(criteria);
//    }else {
//      //title이 있으면 제목검색을 함
//      Profiles = profileDao.findAll(criteria);
//    }
//
//    return Profiles;
//  }




  public int store(String username, String usernick, MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//    todo: filedb 수정 (title, content)
    Profile profile = new Profile(fileName, file.getContentType(), file.getBytes(), username, usernick);

    logger.info(" fileName {} " , fileName);

    return profileDao.save(profile);
  }




  public Profile getFile(String id) {
    return profileDao.findById(id);
  }




//  todo:
  public Stream<Profile> getAllFiles(Criteria criteria) {

//    todo:
    int totalCount = profileDao.selectTotalCount(criteria);

//		Tutorials 총 건수
    criteria.setTotalItems(totalCount);
    //	테이블 총 건수 / 페이지당 출력할 데이터 개수(size)
    criteria.setTotalPages(totalCount/criteria.getSize());
    //    list -> stream 객체로 변환
    Stream<Profile> resFileDB = profileDao.findAll(criteria).stream();

    return resFileDB;
  }




  public boolean deleteById(Long id) {

    int queryResult = 0;

    queryResult = profileDao.deleteFile(id);

    return (queryResult >= 1) ? true : false;
  }



}
