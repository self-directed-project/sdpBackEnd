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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static com.example.sdpBackEnd.excetion.StatusEnum.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;


    @PostMapping("/login")
    public ResponseEntity<MemberResponseDto> login(@Validated @RequestBody final MemberRequestDto memberRequestDto
            , BindingResult bindingResult, HttpServletRequest request) {

        //DTO 유효성 실패Exception처리
        if (bindingResult.hasErrors()) {
            throw new CustomException(StatusEnum.BAD_REQUEST);
        }

        Member member = memberService.login(memberRequestDto);

        //로그인 성공 시 - 세션 발급
        sessionManager.createSession(request, member);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberResponseDto(OK));

    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity<MemberResponseDto> logincheck(HttpServletRequest request) {


        //로그아웃 성공 시 - 세션 기간 만료설정
        sessionManager.Logout(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MemberResponseDto(Logout_OK));

    }
}
