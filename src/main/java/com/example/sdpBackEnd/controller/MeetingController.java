package com.example.sdpBackEnd.controller;


import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.dto.MeetingMemberResponseDto;
import com.example.sdpBackEnd.dto.MeetingResponseDto;
import com.example.sdpBackEnd.dto.MeetingSearchDto;
import com.example.sdpBackEnd.dto.*;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.service.MeetingMemberService;
import com.example.sdpBackEnd.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.example.sdpBackEnd.excetion.StatusEnum.MEETING_ALL_OK;
import static com.example.sdpBackEnd.excetion.StatusEnum.MEETING_My_OK;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {
    @Autowired
    private final MeetingService meetingService;
    private final MeetingMemberService meetingMemberService;

    private final SessionManager sessionManager;

    //미팅 전체 조회
//    @GetMapping("/all")
//    public ResponseEntity<MeetingResponseDto> findAllMeetings(HttpServletRequest request){
//
//        sessionManager.CheckSession(request);
//
//        List<Meeting> meetings = meetingService.findAllMeetings();
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new MeetingResponseDto(MEETING_ALL_OK,meetings));
//    }

    // 미팅 전체 조회 페이징 처리
    @GetMapping("/all")
    public ResponseEntity<MeetingResponseDto> _findAllMeetings(HttpServletRequest request, @PageableDefault(size=4) Pageable pageable){
        sessionManager.CheckSession(request);
        Page<MeetingMemberDto> meetings = meetingService.findAll(pageable);
        System.out.print(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MeetingResponseDto(MEETING_ALL_OK,meetings));
    }

    //나의 미팅조회
    @GetMapping("/mymeeting")
    public ResponseEntity<MeetingMemberResponseDto> findMyMeetings(HttpServletRequest request){

        Long memberId=sessionManager.CheckSession(request);

        List<MeetingMemberDto> MyMeetings =meetingMemberService.findMyMeeting(memberId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MeetingMemberResponseDto(MEETING_My_OK,MyMeetings));
    }


    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    @PostMapping("/room")
    public List<Meeting> findAllMeetingsRoom(@RequestBody MeetingSearchDto meetingSearchDto ){
        List<Meeting> meetings = meetingService.findMeetingInRoom(meetingSearchDto.getStart(), meetingSearchDto.getEnd(), meetingSearchDto.getId());
        return meetings;
    }

}