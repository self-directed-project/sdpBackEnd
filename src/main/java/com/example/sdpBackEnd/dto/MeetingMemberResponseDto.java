package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MeetingMemberResponseDto {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;
    private final List<MeetingMemberDto> MyMeetings;


    public MeetingMemberResponseDto(StatusEnum statusEnum, List<MeetingMemberDto> meetings) {
        this.status = statusEnum.getStatus().value();
        this.code = statusEnum.name();
        this.message = statusEnum.getMessage();
        this.MyMeetings = meetings;
    }
}
