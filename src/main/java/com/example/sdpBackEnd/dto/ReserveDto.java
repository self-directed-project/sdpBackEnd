package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.MeetingType;
import com.example.sdpBackEnd.entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReserveDto {

    private String name;

    private List<Long> membersId;

    private long meetingRoomId;

    private MeetingType type;

    private LocalDateTime start;

    private LocalDateTime end;

    private String description;



    //String name, List<Member> members, long meetingRoomId, MeetingType type, LocalDateTime start, LocalDateTime end
}
