package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
//테스트용
@AllArgsConstructor
public class MemberRequestDto {
    private String name;
    private String username;
    private String password;

    public Member ToEntity(){
        return Member.builder()
                .name(name)
                .username(username)
                .password(password)
                .build();
    }
}
