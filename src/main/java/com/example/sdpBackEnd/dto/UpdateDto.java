package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.MeetingType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateDto {

    private long id;

    private String name;

    private List<Long> membersId;

    private long meetingRoomId;

    private MeetingType type;

    private LocalDateTime start;

    private LocalDateTime end;

    private String description;
}
