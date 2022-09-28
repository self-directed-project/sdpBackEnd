package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.dto.MemberResponseDto;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.example.sdpBackEnd.excetion.StatusEnum.OK;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private static final String LOGIN_MEMBER="LOGIN_MEMBER";
    private final SessionManager sessionManager;


    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@Validated @RequestBody final MemberRequestDto memberRequestDto
            , BindingResult bindingResult, HttpServletRequest request) {


        //DTO 유효성 실패Exception처리
        if (bindingResult.hasErrors()) {
            System.out.println(memberRequestDto.getUsername());
            System.out.println(memberRequestDto.getPassword());
            throw new CustomException(StatusEnum.BAD_REQUEST);
        }

        //로그인 성공 시
        Member member= memberService.login(memberRequestDto);

        sessionManager.createSession(request,member);

        sessionManager.LoginSession(request);


        //세션 조회 성공 시
        if(sessionManager.LoginSession(request)){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MemberResponseDto(OK));
        }
        //세션 조회 실패 Exception처리
        throw new CustomException(StatusEnum.BAD_REQUEST_Session);
    }

}
