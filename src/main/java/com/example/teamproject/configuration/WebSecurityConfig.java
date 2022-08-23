package com.example.teamproject.configuration;

import com.example.teamproject.security.jwt.AuthEntryPointJwt;
import com.example.teamproject.security.jwt.AuthTokenFilter;
import com.example.teamproject.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// 글로벌 웹 보안에 클래스를 찾아 자동으로 적용
@EnableWebSecurity
// AOP 보안을 제공
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
//          사용자 세부 정보 : 인증을 위해 사용
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }

  // 권한 처리
  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    vue -> spring ( ROLE_ADMIN / ROLE_USER 권한 체크 )
//    사용자정보( id , pwd, 이름, email, 롤(권한) ) : userDetails, 어소리티
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

//  스프링 컴포넌트 만들기
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

//  암호화 메소드
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  todo: vue + springboot 합칠시 아래 필요
  @Override
  public void configure(WebSecurity webSecurity) throws Exception {
    // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
    webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    REST API ( axios )
    http.cors().and().csrf().disable()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and() // 예외처리
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 세션안씀

//            todo: 아래 보안 설정 수정 "/api/auth/**" -> "/api/**"
      .authorizeRequests().antMatchers("/api/**").permitAll()
//      .antMatchers("/api/test/**").permitAll()
//            todo: vue + springboot 통합되었으므로 아래 수정
      .antMatchers("/login", "/home", "/", "/register").permitAll()
      .anyRequest().authenticated();

//    세션 필터를 안쓰므로 웹토큰 필터를 끼워넣음
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}
