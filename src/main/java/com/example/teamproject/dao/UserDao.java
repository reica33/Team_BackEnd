package com.example.teamproject.dao;

import com.example.teamproject.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.dao
 * fileName : UserDao
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
@Mapper
public interface UserDao {
    //id로 유저 가져오기 메소드
    Optional<User> findById(String id);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernick(String usernick);

    int existsByUsername(String username);

    int existsByEmail(String email);

    int insertUser(User user);
}
