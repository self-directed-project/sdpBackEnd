package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Member;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {

    private String username;
    private String name;
    private String password;

    public SessionUser(Member member) {

        this.name=member.getName();
    }
}
