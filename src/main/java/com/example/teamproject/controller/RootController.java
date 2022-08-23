package com.example.teamproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName : com.example.bbiyosi.controller
 * fileName : Rootcontroller
 * author : ds
 * date : 2022-06-14
 * description : vue 랑 react 같은 싱글페이지 어플리케이션은 새로고침 하면 오류가 나기 때문에 RootController 필요함
 *              에러 처리하는 방법 3가지 중 1개가 Root Controller
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-14         ds          최초 생성
 */
@Controller
public class RootController implements ErrorController {
    //Not found 에러가 발생하면 강제적으로 index.html 주소로 변경(redirect)
    @GetMapping("/error")
    public String redirectRoot() {
        return "index.html";
    }
}

