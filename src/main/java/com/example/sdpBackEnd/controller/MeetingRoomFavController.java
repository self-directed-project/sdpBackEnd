package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.service.MeetingRoomFavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/meeting-rooms")
@RestController
public class MeetingRoomFavController {

    private final MeetingRoomFavService meetingRoomFavService;


    @PostMapping
    public ResponseEntity<MeetingRoomFavDto> fav(@RequestBody MeetingRoomFavDto meetingRoomFavDto){

        meetingRoomFavService.favPost(meetingRoomFavDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> findMeetingRooms (@RequestBody MeetingRoomFavDto meetingRoomFavDto){

        List<FavRoomDto> favRoomDtoList = meetingRoomFavService.getFavMeetingRooms(meetingRoomFavDto);
        List<FavRoomDto> nonFavRoomDtoList = meetingRoomFavService.getNonFavMeetingRooms(meetingRoomFavDto);

        Map<String, Object> result = new HashMap<>();
        result.put("fav",favRoomDtoList);
        result.put("nonFav",nonFavRoomDtoList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
