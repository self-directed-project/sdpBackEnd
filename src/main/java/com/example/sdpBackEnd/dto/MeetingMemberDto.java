package com.example.sdpBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Getter
public class MeetingMemberDto {

//    회의명/회의일시/회의실/개설자

    private String name;
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start;
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end;
    private Long meetingRoomId;
    private String type;
    private String createdBy;

}
