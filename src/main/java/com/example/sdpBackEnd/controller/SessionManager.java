package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Component
@RequiredArgsConstructor
public class SessionManager {


    public static final String SESSION_COOKIE_NAME = "mySessionId";

    //session 생성,보관
    public void createSession(HttpServletRequest request, Member member) {

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_COOKIE_NAME, member);
    }

    //유저의 session 조회 - 클라이언트 재접속 시
    public Long CheckSession(HttpServletRequest request) {

        HttpSession session;
        Member loginMember;

        //받아온 세션이 없거나 등록된 세션이 아닌 경우
        try {
            session = request.getSession(true);
            System.out.println(session);
            loginMember = (Member) session.getAttribute(SESSION_COOKIE_NAME);
        } catch (Exception e) {
            throw new CustomException(StatusEnum.GET_NOT_FOUND_SID);
        }


        return loginMember.getId();

    }

    //로그아웃 - session기한 만료!!
    public void Logout(HttpServletRequest request) {

        HttpSession session;

        try {
            session = request.getSession(false);

        } catch (Exception e) {
            throw new CustomException(StatusEnum.GET_NOT_FOUND_SID);
        }

        session.invalidate();
    }
}
