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

import javax.servlet.http.HttpServletRequest;

import static com.example.sdpBackEnd.excetion.StatusEnum.OK;
import static com.example.sdpBackEnd.excetion.StatusEnum.SESSION_OK;

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

        Member member= memberService.login(memberRequestDto);

        //로그인 성공 시
        sessionManager.createSession(request,member);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MemberResponseDto(OK));

    }

    @GetMapping("/main")
    public ResponseEntity<MemberResponseDto> logincheck( HttpServletRequest request) {


        //세션조회
        sessionManager.CheckSession(request);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MemberResponseDto(SESSION_OK));

    }

}
