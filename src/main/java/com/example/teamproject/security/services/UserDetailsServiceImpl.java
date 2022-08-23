package com.example.teamproject.security.services;

import com.example.teamproject.dao.UserDao;
import com.example.teamproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserDao userDao;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    db tb_user 테이블 확인
    User user = userDao.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

//  응답 포함 JWT 및 UwerDetails 데이터
//    Role role = new Role();
//    role.setName(user.getRole());
//
    Set<String> roles = new HashSet<>();

//    테스트
//    roles.add(role);
    roles.add(user.getRole());
    user.setRoles(roles);

//    스프링시큐리티 권한이 설정됨
    return UserDetailsImpl.build(user);
  }

}
