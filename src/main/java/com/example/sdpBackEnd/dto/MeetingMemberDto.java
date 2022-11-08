package com.example.sdpBackEnd.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class MeetingMemberDto {

//    회의명/회의일시/회의실/개설자

    private String name;
    private LocalDateTime start;
    private Long meetingRoomId;
    private String createdBy;

}
