package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //추후에 spring security 적용
    @Transactional
    public Long save(final MemberRequestDto param) {

        try {
            return memberRepository.findMember(param.getUsername(), param.getPassword())
                    .getId();
        } catch (Exception e) {
            System.out.println("id와 password를 확인해주세요");
            return null;
        }


    }
}
