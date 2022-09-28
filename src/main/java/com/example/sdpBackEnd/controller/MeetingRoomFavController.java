package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.service.MeetingRoomFavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<FavRoomDto>> findMeetingRooms (@RequestBody MeetingRoomFavDto meetingRoomFavDto){

        List<FavRoomDto> favRoomDtoList = meetingRoomFavService.getFavMeetingRooms(meetingRoomFavDto);

        return new ResponseEntity<>(favRoomDtoList, HttpStatus.OK);
    }
}
