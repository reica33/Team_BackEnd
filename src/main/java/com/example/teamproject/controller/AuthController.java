package com.example.teamproject.controller;

import com.example.teamproject.model.ERole;
import com.example.teamproject.model.Role;
import com.example.teamproject.model.User;
import com.example.teamproject.payload.request.LoginRequest;
import com.example.teamproject.payload.request.SignupRequest;
import com.example.teamproject.payload.response.JwtResponse;
import com.example.teamproject.payload.response.MessageResponse;
import com.example.teamproject.security.jwt.JwtUtils;
import com.example.teamproject.security.services.UserDetailsImpl;
import com.example.teamproject.service.RoleServiceImpl;
import com.example.teamproject.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//import static org.graalvm.compiler.options.OptionType.User;

// 아무 주소나 다 받겠다 (origins = "*") -> 보안이 뚫린다 -> 강사님 왈 : 실무에서는 쓰지 마세욤 ㅠ (0721)
// maxAge = 3600 이 기간동안만 들어와서 통용되게 하겠다 -> 강사님 왈 : 별로 의미가 없다 (0721)
@CrossOrigin(origins = "*", maxAge = 3600)
// TODO : @CrossOrigin("http://192.168.0.166:8080")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

//  사용자 이름, 비밀번호 인증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//  SecurityContext 를 사용하여 업데이트 Authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
//  JWT 토큰 생성
        String jwt = jwtUtils.generateJwtToken(authentication);

//  유저 상세 정보( UserDetails ) 가져오기
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

//  응답 포함 JWT 및 UwerDetails 데이터
        List<String> role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

//    테스트
//    roles.add("ROLE_USER");


        logger.info("loginRequest authenticateUser {} ", role);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getUsernick(),
                userDetails.getEmail(),
                role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        logger.info("signUpRequest {} ", signUpRequest);

        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getUsernick(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

//    Set<String> strRoles = signUpRequest.getRoles();
        String strRoles = signUpRequest.getRole();
//    Set<Role> roles = new HashSet<>();
        Set<String> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole.getName().name());
//      user 객체 저장
            user.setRole(userRole.getName().name());
        } else {
//      strRoles.forEach(role -> {
            switch (strRoles) {
                case "ROLE_ADMIN":
                    Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole.getName().name());
//      user 객체 저장
                    user.setRole(adminRole.getName().name());
                    break;
                case "ROLE_MODERATOR":
                    Role modRole = roleService.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole.getName().name());
//      user 객체 저장
                    user.setRole(modRole.getName().name());
                    break;
                default:
                    Role userRole = roleService.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole.getName().name());
                    //      user 객체 저장
                    user.setRole(userRole.getName().name());
            }
//      });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("회원가입이 완료되었습니다!"));
    }
}
