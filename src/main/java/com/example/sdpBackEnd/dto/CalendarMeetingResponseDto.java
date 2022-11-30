package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CalendarMeetingResponseDto {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;
    private final List<CalendarMeetingDto> calendarMeetings;

    public CalendarMeetingResponseDto(StatusEnum statusEnum, List<CalendarMeetingDto> calendarMeetings) {
        this.status = statusEnum.getStatus().value();
        this.code = statusEnum.name();
        this.message = statusEnum.getMessage();
        this.calendarMeetings = calendarMeetings;
    }
}
