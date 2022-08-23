package com.example.teamproject.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName : com.example.dongsungsi.controller
 * fileName : WebConfig
 * author : kangtaegyung
 * date : 2022/06/14
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/14         kangtaegyung          최초 생성
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                아래 url 허용
                .allowedOrigins("http://localhost:8080")
//                .allowedOrigins("http://192.168.0.166:8080")
                //  TODO : .allowedOrigins("http://192.168.0.166:8080")
//                Todo: 아래 추가해야 update, delete, insert, select 가 cors 문제가 안생김
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name()
                );
    }

    // 예전 WebConfig (이거는 업로드 딜리트 불가)
//    public class WebConfig implements WebMvcConfigurer {
//
//        //@CrossOrigin 어노테이션과 동일함
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
////        WebMvcConfigurer.super.addCorsMappings(registry);
//            registry.addMapping("/**")
//                    //아래 주소로 들어오는 요청은 보안 허용
//                    // .allowedOrigins("http://localhost:8080");
//                    .allowedOrigins("http://192.168.0.166");
//        }
//
//    }
}
