package com.example.sdpBackEnd.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DateDto {
    private LocalDateTime start;
    private LocalDateTime end;
}