package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class SessionManager {

  public static final String SESSION_COOKIE_NAME = "mySessionId";

    //session 생성,보관
    public void createSession(HttpServletRequest request,Member member) {

        HttpSession session=request.getSession();
        session.setAttribute(SESSION_COOKIE_NAME, member);
        System.out.println(member.getName()+"의 세션 id :"+session.getId());

    }


    //유저의 session 조회 - 클라이언트 재접속 시
    public void LoginSession(HttpServletRequest request) {


        HttpSession session = request.getSession(false);

        System.out.println(session.getId());

        if (session == null) {
            System.out.println("/브라우저에서 받은 세션이 없음 - 로그인페이지로 이동");
            throw new CustomException(StatusEnum.BAD_REQUEST_Session_DOES_NOT_EXIST);
        }

        Member loginMember = (Member) session.getAttribute(SESSION_COOKIE_NAME);
        if (loginMember == null) {
            System.out.println("/등록된 세션이 없습니다."+session.getId()+"로그인페이지로 이동");
            throw new CustomException(StatusEnum.GET_NOT_FOUND_SID);
        }
        System.out.println(loginMember.getName()+loginMember.getUsername());
        System.out.println("/메인페이지로 이동\t"+ loginMember.getName()+"로그인 완료");
    }


}
