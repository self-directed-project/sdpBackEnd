package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Meeting;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Builder
@Getter
public class MeetingMemberDto {

//    회의명/회의일시/회의실/개설자

    private long meetingId;
    private String name;
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start;
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end;
    private Long meetingRoomId;
    private String meetingRoomName;
    private String type;
    private String createdBy;

//    public static MeetingMemberDto from (Meeting meeting){
//        return MeetingMemberDto.builder()
//                .meetingId(meeting.getId())
//                .name(meeting.getName())
//                .start(meeting.getStart())
//                .end(meeting.getEnd())
//                .meetingRoomId(meeting.getMeetingRoom().getId())
//                .meetingRoomName(meetingRoomRepository.findById(meeting.getMeetingRoom().getId()).get().getName())
//                .createdBy(memberRepository.findById(meeting.getCreatedBy()).get().getName())
//                .type(meeting.getMeetingType().toString())
//                .build();
//    }

}
