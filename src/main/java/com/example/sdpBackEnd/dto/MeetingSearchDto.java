package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingRoom;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeetingSearchDto {
    private LocalDateTime start;
    private LocalDateTime end;
    private long id;
}
