package com.example.sdpBackEnd.dto;

import java.time.LocalDateTime;

import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;

  /*  private  String name;*/

    public MemberResponseDto(StatusEnum statusEnum) {
        this.status = statusEnum.getStatus().value();
        this.code = statusEnum.name();
        this.message = statusEnum.getMessage();

    }

 /*   public MemberResponseDto(StatusEnum statusEnum, Member member) {
        this.status = statusEnum.getStatus().value();
        this.code = statusEnum.name();
        this.message = statusEnum.getMessage();
        this.name=member.getName();
    }
*/
}