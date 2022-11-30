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

    @DateTimeFormat(pattern = "yy.MM.dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime startDate;

    @DateTimeFormat(pattern = "HH")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH", timezone = "Asia/Seoul")
    private LocalDateTime startHour;

    @DateTimeFormat(pattern = "mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm", timezone = "Asia/Seoul")
    private LocalDateTime startMinute;

    @DateTimeFormat(pattern = "yy.MM.dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    @DateTimeFormat(pattern = "HH")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH", timezone = "Asia/Seoul")
    private LocalDateTime endHour;

    @DateTimeFormat(pattern = "mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm", timezone = "Asia/Seoul")
    private LocalDateTime endMinute;

}
