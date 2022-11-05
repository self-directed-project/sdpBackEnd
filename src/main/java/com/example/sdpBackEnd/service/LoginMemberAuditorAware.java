package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class LoginMemberAuditorAware implements AuditorAware<String> {

    Member member = Member.builder()
            .id((long)1)
            .name("asd")
            .build();
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(member.getName());
    }
}
