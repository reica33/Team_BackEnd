package com.example.teamproject.service;

import com.example.teamproject.dao.UserDao;
import com.example.teamproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : UserServiceImpl
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public Optional<User> findByUsername(String usernick) {
        Optional<User> resUser = userDao.findByUsernick(usernick);

        return resUser;
    }

    @Override
    public Boolean existsByUsername(String username) {
        int queryResult = 0;

        queryResult = userDao.existsByUsername(username);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public Boolean existsByEmail(String email) {

        int queryResult = 0;

        queryResult = userDao.existsByEmail(email);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public Boolean save(User user) {
        int queryResult = 0;

        queryResult = userDao.insertUser(user);

        return (queryResult >= 1) ? true : false;
    }
}