package com.example.teamproject.dao;

import com.example.teamproject.model.FileDB;
import com.example.teamproject.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDBDao {
    FileDB findById(String id);
//    todo:
    List<FileDB> findAll(Criteria criteria);
// TODO 추가
    //    전체 회원 조회 메소드
//    List<FileDB> findByTitleContaining(Criteria criteria);
    int selectTotalCount();
    int save(FileDB fileDB);
//    TODO 삭제
    int deleteById(Long id);
}
