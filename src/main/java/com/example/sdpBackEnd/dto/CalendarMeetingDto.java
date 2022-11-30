package com.example.sdpBackEnd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class CalendarMeetingDto {

    //미팅룸아이디 (1회의실,2회의실 ...)
    private Long meetingRoomId;

    //미팅 시작 시간
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start;

    //미팅 끝 시간
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end;

    // 미팅 타입
    private String type;

    //미팅 이름
    private String name;

}


