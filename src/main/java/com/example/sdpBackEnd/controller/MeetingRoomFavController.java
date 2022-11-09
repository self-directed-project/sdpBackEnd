package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.entity.MeetingRoomFav;
import com.example.sdpBackEnd.service.MeetingRoomFavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/meeting-rooms")
@RestController
@Transactional
public class MeetingRoomFavController {

    private final MeetingRoomFavService meetingRoomFavService;

    private final SessionManager sessionManager;


    /*@PostMapping
    public ResponseEntity<MeetingRoomFavDto> fav(@RequestBody MeetingRoomFavDto meetingRoomFavDto){

        meetingRoomFavService.favPost(meetingRoomFavDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }*/


    @PostMapping
    public ResponseEntity<Map<String, Object>> fav(HttpServletRequest request, @RequestBody MeetingRoomFavDto favDto){

        long memberId = sessionManager.CheckSession(request);

        MeetingRoomFavDto meetingRoomFavDto = MeetingRoomFavDto.builder()
                .memberId(memberId)
                .meetingRoomId(favDto.getMeetingRoomId())
                .build();

        meetingRoomFavService.favPost(meetingRoomFavDto);

        List<FavRoomDto> favRoomDtoList = meetingRoomFavService.getFavMeetingRooms(meetingRoomFavDto);
        List<FavRoomDto> nonFavRoomDtoList = meetingRoomFavService.getNonFavMeetingRooms(meetingRoomFavDto);

        Map<String, Object> result = new HashMap<>();
        result.put("fav",favRoomDtoList);
        result.put("nonFav",nonFavRoomDtoList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

/*    @PostMapping
    public String fav(@RequestBody MeetingRoomFavDto meetingRoomFavDto){

        meetingRoomFavService.favPost(meetingRoomFavDto);

        return "meeting-rooms";
    }*/

    @GetMapping
    public ResponseEntity<Map<String, Object>> findMeetingRooms (HttpServletRequest request){

        long memberId = sessionManager.CheckSession(request);

        MeetingRoomFavDto meetingRoomFavDto = MeetingRoomFavDto.builder()
                .memberId(memberId)
                .build();

        List<FavRoomDto> favRoomDtoList = meetingRoomFavService.getFavMeetingRooms(meetingRoomFavDto);
        List<FavRoomDto> nonFavRoomDtoList = meetingRoomFavService.getNonFavMeetingRooms(meetingRoomFavDto);

        Map<String, Object> result = new HashMap<>();
        result.put("fav",favRoomDtoList);
        result.put("nonFav",nonFavRoomDtoList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
