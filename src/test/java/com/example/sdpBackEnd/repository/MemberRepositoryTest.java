package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    private Member m;
    @BeforeEach
    public void setup(){
        m = memberRepository.save(Member.builder()
                .password("1234")
                .name("tsunami")
                .username("ysm")
                .build());
    }

    @Test
    void member_저장하기() throws Exception {

        LocalDateTime now = LocalDateTime.now();

        List<Member> memberList = memberRepository.findAll();
        Member m1 = memberList.get(0);

        System.out.println("________createDate=" + m1.getCreatedDate());
        assertThat(m.getCreatedDate().isAfter(now));
        assertEquals("tsunami", m.getName());
    }

    @Test
    void member_조회하기() throws Exception {

        try {
            Member m = memberRepository.findMember("ysm", "134");
            System.out.println(m.getName());
        } catch (Exception e) {
            System.out.println("정보조회 실패");
        }
    }

}