package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.MeetingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMeetingDto {

    private long id;

    private long meetingRoom;

    private String name;

    private String description;

    private MeetingType meetingType;

    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start;

    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end;

}
