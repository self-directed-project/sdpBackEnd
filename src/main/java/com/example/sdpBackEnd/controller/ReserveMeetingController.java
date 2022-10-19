package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.dto.ReserveDto;
import com.example.sdpBackEnd.service.ReserveMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
