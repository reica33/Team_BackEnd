package com.example.teamproject.service;

import com.example.teamproject.model.ERole;
import com.example.teamproject.model.Role;

import java.util.Optional;

/**
 * packageName : com.example.taegyungsi.service
 * fileName : RoleService
 * author : kangtaegyung
 * date : 2022/05/23
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/23         kangtaegyung          최초 생성
 */
public interface RoleService {
    Optional<Role> findByName(ERole name);
}
