package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MeetingRoomDto;
import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.dto.ReserveDto;
import com.example.sdpBackEnd.entity.MeetingType;
import com.example.sdpBackEnd.service.ReserveMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/meeting/reserve")
@RestController
public class ReserveMeetingController {

    private final ReserveMeetingService reserveMeetingService;

    @PostMapping
    public ResponseEntity<ReserveDto> reserve(@RequestBody ReserveDto reserveDto){

        reserveMeetingService.makeMeeting(reserveDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> meetingRoomAndType(){

        Map<String, Object> result = new HashMap<>();

        List<MeetingRoomDto> meetingRoomList = reserveMeetingService.allMeetingRoom();
        Class meetingType = MeetingType.class;

        result.put("meetingRoomList", meetingRoomList);
        result.put("meetingType", meetingType.getEnumConstants());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
