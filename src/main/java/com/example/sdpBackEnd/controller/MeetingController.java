package com.example.sdpBackEnd.controller;


import com.example.sdpBackEnd.dto.DateDto;
import com.example.sdpBackEnd.dto.MeetingSearchDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {
    @Autowired
    private final MeetingService meetingService;

//    @GetMapping("/meetings")
//    public String meetingList(Long meetingRoomId, Model model){
//        model.addAttribute("meetinglist",meetingService.findMeetingByMeetingRoom(meetingRoomId));
//        return "meeting";
//    }

    //모든 미팅 찾아옴
    @GetMapping("/all")
    public List<Meeting> findAllMeetings() {
        List<Meeting> meetings = meetingService.findAllMeetings();
        return meetings;
    }

    //미팅 기간에 따라 찾아옴
    @PostMapping("/period")
    public List<Meeting> findAllMeetingsPeriod(@RequestBody DateDto dateDto) {
        List<Meeting> meetings = meetingService.findMeetingInPeriod(dateDto.getStart(), dateDto.getEnd());
        return meetings;
    }

    //미팅 기간 + 회의실 종류에 따라 찾아옴
    @PostMapping("/room")
    public List<Meeting> findAllmeetingsRoom(@RequestBody MeetingSearchDto meetingSearchDto ){
        List<Meeting> meetings = meetingService.findMeetingInRoom(meetingSearchDto.getStart(), meetingSearchDto.getEnd(), meetingSearchDto.getId());
        return meetings;
    }
}
