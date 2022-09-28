package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.entity.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
public class HomeController {

    @GetMapping("/")//파라미터 전달 시?
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER,
            required = false) Member member) {
        if (member == null) {
            return "세션아이디가 없습니다. 로그인페이지로 이동";
        }

        return "세션있습니다. 유지성공 홈으로 이동";

    }
}
