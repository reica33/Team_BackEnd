package com.example.teamproject.service;

import com.example.teamproject.dao.RoleDao;
import com.example.teamproject.model.ERole;
import com.example.teamproject.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : RoleServiceImpl
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao;

    @Override
    public Optional<Role> findByName(ERole name) {

        return roleDao.findByName(name);
    }
}
