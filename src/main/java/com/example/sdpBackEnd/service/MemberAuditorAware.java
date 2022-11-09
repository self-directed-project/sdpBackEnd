package com.example.sdpBackEnd.service;


import com.example.sdpBackEnd.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MemberAuditorAware implements AuditorAware<Long> {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private final HttpSession session;
    @Override
    public Optional<Long> getCurrentAuditor() {
        Member loginMember = (Member) session.getAttribute(SESSION_COOKIE_NAME);

        //조회기능 -sessionMange객체로 구현하기
        if (loginMember == null) {
            System.out.println("조회된 내용이 없습니다..");
            return null;
        }
        System.out.println("조회 성공");
        return Optional.ofNullable(loginMember.getId());
    }
}