package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Component
@RequiredArgsConstructor
public class SessionManager {

  public static final String SESSION_COOKIE_NAME = "mySessionId";


    //session 생성,보관
    public void createSession(HttpServletRequest request,Member member) {

        HttpSession session=request.getSession();
        if (session.isNew()) {
            System.out.println("/새롭게 생성된 session");
        }
        session.setAttribute(SESSION_COOKIE_NAME, member);
        System.out.println("세션 id :"+session.getId());


    }

    //session 조회 - 클라이언트 재접속 시
    public boolean LoginSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("/로그인페이지로 이동");
            return false;
        }
        Member loginMember = (Member) session.getAttribute(SESSION_COOKIE_NAME);
        if (loginMember == null) {
            System.out.println("/등록된 세션이 없습니다."+session.getId()+"로그인페이지로 이동");
            return false;
        }

        System.out.println("/메인페이지로 이동");
        return true;


    }

    //session 삭제
    public void LogoutSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        System.out.println("/로그인페이지로 이동");

    }
}
