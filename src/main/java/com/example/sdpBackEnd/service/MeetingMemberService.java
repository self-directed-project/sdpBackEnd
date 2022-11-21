package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
@RequiredArgsConstructor
public class MeetingMemberService {

    private final MeetingMemberRepository meetingMemberRepository;
    private final MemberRepository memberRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;

    //
    public List<MeetingMemberDto> findMyMeeting(Long memberId) {

        List<MeetingMemberDto> meetings =
                meetingMemberRepository.findMeetingMemberByMemberId(memberId).stream()
                        .map(meetingmember -> MeetingMemberDto.builder()
                                .meetingId(meetingmember.getMeeting().getId())
                                .name(meetingmember.getMeeting().getName())
                                .start(meetingmember.getMeeting().getStart())
                                .end(meetingmember.getMeeting().getEnd())
                                .meetingRoomId(meetingmember.getMeeting().getMeetingRoom().getId())
                                .meetingRoomName(meetingRoomRepository.findById(meetingmember.getMeeting().getMeetingRoom().getId()).get().getName())
                                .createdBy(memberRepository.findById(meetingmember.getMeeting().getCreatedBy()).get().getName())
                                .type(meetingmember.getMeeting().getMeetingType().toString())
                                .build()
                        ).collect(Collectors.toList());

        if (meetings.isEmpty()) {
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
        return meetings;
    }

    public Map<String, List<String>> viewdatailmeeting(
            @RequestParam long meetingRoomId,
            @RequestParam String start) {

        //참석자,회의내용
        Map<String, List<String>> detail = new HashMap<>();
        Optional<Meeting> m;
        List<String> nameList;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime datetime = LocalDateTime.parse(start, formatter);

        try{
             m=meetingRepository.findByMeetingRoomIdAndStart(meetingRoomId,datetime);
        }catch (Exception e){
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
        List<String> descript = Arrays.asList(m.get().getDescription());
        try{
            nameList= meetingMemberRepository.findByMeetingId(m.get().getId())
                    .stream()
                    .map(meetingMember ->meetingMember.getMember().getName()
                    ).collect(Collectors.toList());
        }catch (Exception e){
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }



        detail.put("nameList", nameList);
        detail.put("descript",descript);

        System.out.println("회의실"+m.get().getMeetingRoom().getId()+"\t"+
                "회의시작시간"+m.get().getStart());

        return detail;

    }

}

