package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //httpsession 추가
    @PostMapping("/login")
    public Long login(@RequestBody final MemberRequestDto memberRequestDto) {
        return memberService.login(memberRequestDto);
    }
}
