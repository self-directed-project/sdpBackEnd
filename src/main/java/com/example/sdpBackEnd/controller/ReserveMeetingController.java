package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.*;
import com.example.sdpBackEnd.entity.MeetingType;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.service.ReserveMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/meeting")
@RestController
public class ReserveMeetingController {

    private final ReserveMeetingService reserveMeetingService;
    private final SessionManager sessionManager;

    //회의 예약
    @PostMapping("/reserve")
    public ResponseEntity<ReserveDto> reserve(@RequestBody ReserveDto reserveDto){

        System.out.println(reserveDto.getName()+"\n"+reserveDto.getDescription()+"\n"+reserveDto.getStart()+"\n"+reserveDto.getEnd()+"\n"+reserveDto.getType()+"\n"+reserveDto.getMeetingRoomId()+"\n"+reserveDto.getMembersId());

        reserveMeetingService.makeMeeting(reserveDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //예약버튼 누를 시 회의실과 미팅타입 초기값 전달
    @GetMapping("/reserve")
    public ResponseEntity<Map<String, Object>> meetingRoomAndType(){

        Map<String, Object> result = new HashMap<>();

        List<MeetingRoomDto> meetingRoomList = reserveMeetingService.allMeetingRoom();
        Class meetingType = MeetingType.class;

        List<SearchMemberDto> searchMemberDtoList = reserveMeetingService.AllMembers();

        result.put("meetingRoomList", meetingRoomList);
        result.put("meetingType", meetingType.getEnumConstants());
        result.put("memberList", searchMemberDtoList);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //이름 검색시 이름 포함하는 리스트 전달
    @GetMapping("/reserve/search")
    public ResponseEntity<List<SearchMemberDto>> searchMember(@RequestParam String name){
        List<SearchMemberDto> searchList = reserveMeetingService.searchMember(name);

        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }

    //회의 삭제
    @PostMapping("/delete")
    public ResponseEntity<List<DeleteMeetingDto>> deleteMeeting(HttpServletRequest request, @RequestBody DeleteMeetingDto deleteMeetingDto){

        long id = sessionManager.CheckSession(request);

        reserveMeetingService.deleteMeetingList(id, deleteMeetingDto.getMeetingsId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}