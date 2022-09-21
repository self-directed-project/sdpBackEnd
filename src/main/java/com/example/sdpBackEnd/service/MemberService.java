package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MemberRequestDto;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.ErrorCode;
import com.example.sdpBackEnd.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member login(final MemberRequestDto param) {
        //회원 username존재유무 검사
        if (!memberRepository.existsByUsername(param.getUsername())) {
            throw new CustomException(ErrorCode.POSTS_NOT_FOUND_ID);
        }
        //회원 password 확인
        return memberRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword())
                .orElseThrow(()->new CustomException(ErrorCode.POSTS_NOT_FOUND_PW));

    }
}
