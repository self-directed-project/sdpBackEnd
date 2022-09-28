package com.example.sdpBackEnd.controller;


import com.example.sdpBackEnd.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meeting")
@RequiredArgsConstructor
public class MeetingController {
    @Autowired
    private final MeetingService meetingService;

    @GetMapping("/meeting")
    public String meetingList(Long meetingRoomId, Model model){
        model.addAttribute("meetinglist",meetingService.findMeetingByMeetingRoom(meetingRoomId));
        return "meeting";
    }

}
