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
    public void createSession(HttpServletRequest request,Member member) {

        HttpSession session=request.getSession();
        session.setAttribute(SESSION_COOKIE_NAME, member);
        System.out.println(member.getName()+"의 세션 id :"+session.getId());

    }


    //유저의 session 조회 - 클라이언트 재접속 시
    public Long CheckSession(HttpServletRequest request) {


        if (request==null){
            System.out.println("request 받지 않음");
            throw new CustomException(StatusEnum.BAD_REQUEST_Session_DOES_NOT_EXIST);
        }

        try{
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
            System.out.println("/요청페이지로 이동\t" + loginMember.getId() + ":" + loginMember.getName() + "\tloginCheck");

            return loginMember.getId();
        }catch (Exception e){
            System.out.println("/브라우저에서 받은 세션이 없음 - 로그인페이지로 이동");
            throw new CustomException(StatusEnum.BAD_REQUEST_Session_DOES_NOT_EXIST);
        }

    }
}
