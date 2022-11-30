package com.example.sdpBackEnd.controller;


import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.dto.MeetingMemberResponseDto;
import com.example.sdpBackEnd.dto.MeetingResponseDto;
import com.example.sdpBackEnd.dto.MeetingSearchDto;
import com.example.sdpBackEnd.dto.*;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.service.MeetingMemberService;
import com.example.sdpBackEnd.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.example.sdpBackEnd.excetion.StatusEnum.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private final MeetingService meetingService;
    private final MeetingMemberService meetingMemberService;

    private final SessionManager sessionManager;




    // 미팅 전체 조회 페이징 처리 (?page=0&size=4)
    @GetMapping("/all")
    public ResponseEntity<MeetingResponseDto> _findAllMeetings(HttpServletRequest request, @PageableDefault(size=4) Pageable pageable){
        sessionManager.CheckSession(request);
        List<MeetingMemberDto> meetings = meetingService.findAll();
        System.out.println(meetings);
        Page<MeetingMemberDto> p_Meetings = meetingService.p_FindAll(meetings,pageable);
        if (p_Meetings.isEmpty()){
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MeetingResponseDto(MEETING_ALL_OK, p_Meetings));
    }

    //나의 미팅조회
    @GetMapping("/mymeeting")
    public ResponseEntity<MeetingMemberResponseDto> findMyMeetings(HttpServletRequest request, @PageableDefault(size=4) Pageable pageable){

        Long memberId=sessionManager.CheckSession(request);

        List<MeetingMemberDto> myMeetings =meetingMemberService.findMyMeeting(memberId);
        Page<MeetingMemberDto> p_MyMeetings = meetingMemberService.p_FindMyMeeting(myMeetings,pageable);
        if (p_MyMeetings.isEmpty()){
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MeetingMemberResponseDto(MEETING_My_OK,p_MyMeetings));
    }


    //전체미팅&나의미팅 - 상세페이지 조회
    @GetMapping("/detailPage")
    public  ResponseEntity<MeetingDetailRequestDto> viewDetailPage(@RequestParam Long meetingId) {

        Map<String, List<String>> detail = meetingMemberService.viewdatailmeeting(meetingId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new MeetingDetailRequestDto(MEETING_My_OK,detail));

 
    }


    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    @PostMapping("/calendar")
    public ResponseEntity<CalendarMeetingResponseDto> findMeetingsCalendar(HttpServletRequest request, @RequestBody CalendarMeetingDto calendarMeetingDto){
        sessionManager.CheckSession(request);
        System.out.println("값확인: "+ calendarMeetingDto);

        List<CalendarMeetingDto> calendarMeetings = meetingService.findMeetingCalendar(calendarMeetingDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CalendarMeetingResponseDto(MEETING_Calendar_OK,calendarMeetings));
    }


//    public List<Meeting> findAllMeetingsRoom(@RequestBody MeetingSearchDto meetingSearchDto ){
//        sessionManager.CheckSession(request);
//        List<Meeting> meetings = meetingService.findMeetingInRoom(meetingSearchDto.getStart(), meetingSearchDto.getEnd(), meetingSearchDto.getId());
//        return meetings;
//    }

}