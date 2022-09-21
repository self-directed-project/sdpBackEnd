package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.ErrorCode;
import com.example.sdpBackEnd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private static final String LOGIN_MEMBER="LOGIN_MEMBER";


    @PostMapping("/login")
    public String login(@Validated @RequestBody final MemberRequestDto memberRequestDto
                      , BindingResult bindingResult, HttpServletRequest request) {

        //DTO 유효성 실패 시 Exception처리
        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        Member member= memberService.login(memberRequestDto);

        //로그인 성공 시
        HttpSession session =request.getSession(true);

        session.setAttribute(LOGIN_MEMBER,member);

        return "회원이름: "+member.getName()+"\t세션id: "+session.getId();


    }

    //로그아웃
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            //세션초기화
            session.invalidate();;
        }
        return "로그아웃 성공";
    }
    //회원 접근권한 부여
    public String HomeLogin(HttpServletRequest request) {
        HttpSession session=request.getSession(false);
        if (session == null) {
            return "로그인을 하지 않는 회원의 접근";
        }
        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);
        if (loginMember == null) {
            return "세션 회원 데이터가 없습니다. ";
        }
        //세션에 회원 데이터가 있으면 로그인한 유저를 위한 홈 화면으로 이동
        return "세션조회 성공 홈화면으로 이동합니다.";
    }


}
