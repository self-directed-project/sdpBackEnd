package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService service;
    @Autowired
    private MemberRepository memberRepository;
    private MemberRequestDto requestDto;
    private Member m;
    @BeforeEach
    public void setup(){
        m = memberRepository.save(Member.builder()
                .password("1234")
                .name("tsunami")
                .username("ysm")
                .build());
        requestDto = new MemberRequestDto("tsunami", "ysm", "1234");
        System.out.println(requestDto.getName());
    }

    @Test
    void member_서비스() {
        System.out.println(service.save(requestDto));
    }
}