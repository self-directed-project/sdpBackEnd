package com.example.sdpBackEnd.Domain;

import com.example.sdpBackEnd.entity.MeetingType;
import lombok.*;

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
