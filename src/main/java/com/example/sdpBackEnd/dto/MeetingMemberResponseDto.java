package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MeetingMemberResponseDto {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;
    private final Page<MeetingMemberDto> p_MyMeetings;


    public MeetingMemberResponseDto(StatusEnum statusEnum, Page<MeetingMemberDto> p_MyMeetings) {
        this.status = statusEnum.getStatus().value();
        this.code = statusEnum.name();
        this.message = statusEnum.getMessage();
        this.p_MyMeetings = p_MyMeetings;
    }
}