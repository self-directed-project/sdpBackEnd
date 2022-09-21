package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.service.MeetingRoomFavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/meeting-rooms")
@RestController
public class MeetingRoomFavController {

    private final MeetingRoomFavService meetingRoomFavService;

    @PostMapping
    public ResponseEntity<MeetingRoomFavDto> fav(@RequestBody MeetingRoomFavDto meetingRoomFavDto){

        meetingRoomFavService.favPost(meetingRoomFavDto);

        return new ResponseEntity<>(meetingRoomFavDto, HttpStatus.OK);
    }

   // @GetMapping
}
