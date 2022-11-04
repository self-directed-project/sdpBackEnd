package com.example.sdpBackEnd.controller;


import com.example.sdpBackEnd.dto.MeetingResponseDto;
import com.example.sdpBackEnd.dto.MeetingSearchDto;
import com.example.sdpBackEnd.dto.MyMeetingDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.sdpBackEnd.excetion.StatusEnum.MEETING_ALL_OK;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {
    @Autowired
    private final MeetingService meetingService;
    private final SessionManager sessionManager;

    //미팅 전체 조회
    @GetMapping("/all")
    public ResponseEntity<MeetingResponseDto> findAllMeetings(HttpServletRequest request){

        sessionManager.CheckSession(request);

        List<Meeting> meetings = meetingService.findAllMeetings();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MeetingResponseDto(MEETING_ALL_OK,meetings));
    }

    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    @PostMapping("/room")
    public List<Meeting> findAllMeetingsRoom(@RequestBody MeetingSearchDto meetingSearchDto ){
        List<Meeting> meetings = meetingService.findMeetingInRoom(meetingSearchDto.getStart(), meetingSearchDto.getEnd(), meetingSearchDto.getId());
        return meetings;
    }

}
