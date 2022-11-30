package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.excetion.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Getter
public class MeetingDetailRequestDto {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String code;
    private final String message;
    private final Map<String, List<String>> detail;



    public MeetingDetailRequestDto(StatusEnum statusEnum, Map<String, List<String>> detail) {
        this.status = statusEnum.getStatus().value();
        this.code = statusEnum.name();
        this.message = statusEnum.getMessage();
        this.detail = detail;
    }
}
