package com.example.teamproject.dao;

import com.example.teamproject.model.Profile;
import com.example.teamproject.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileDao {


//    List<Profile> findByUsernameContaining(Criteria criteria);

    Profile findById(String username);
//    todo:
    List<Profile> findAll(Criteria criteria);

    int selectTotalCount(Criteria criteria);
    int save(Profile fileDB);

    int deleteFile(Long id);

    int selectFileDBTotalCount(Profile params);

    List<Profile> selectFileDBList(Profile params);

}
