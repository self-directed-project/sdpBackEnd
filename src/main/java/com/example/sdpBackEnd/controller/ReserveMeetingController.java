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

    @PostMapping("/update")
    public ResponseEntity<?> updateMeeting(HttpServletRequest request, @RequestBody UpdateDto updateDto){

        long memberId = sessionManager.CheckSession(request);

        long meetingId = updateDto.getId();

        ReserveDto reserveDto = ReserveDto.builder()
                .meetingRoomId(updateDto.getMeetingRoomId())
                .name(updateDto.getName())
                .start(updateDto.getStart())
                .end(updateDto.getEnd())
                .description(updateDto.getDescription())
                .type(updateDto.getType())
                .membersId(updateDto.getMembersId())
                .build();

        reserveMeetingService.updateMeeting(memberId, meetingId, reserveDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/update")
    public ResponseEntity<?> giveMeeting(HttpServletRequest request, @RequestParam long meetingId){

        long memberId = sessionManager.CheckSession(request);

        Map<String, Object> result = new HashMap<>();

        List<SearchMemberDto> members = reserveMeetingService.updateSearchMember(meetingId);
        UpdateMeetingDto meeting = reserveMeetingService.updateSearchMeeting(memberId, meetingId);

        result.put("meeting", meeting);
        result.put("members", members);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}