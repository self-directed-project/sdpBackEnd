package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingDto {

    private Long id;

    private Long meetingRoom;

    private String name;

    private MeetingType meetingType;

    private LocalDateTime start;

    private LocalDateTime end;

}
