package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
@RequiredArgsConstructor
public class MeetingMemberService {

    private final MeetingMemberRepository meetingMemberRepository;
    private final MemberRepository memberRepository;
    private final MeetingRoomRepository meetingRoomRepository;

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

    public void viewdatailmeeting(long meetingRoomId, LocalDateTime start) {

        //참석자,회의내용
        Map<String, ArrayList> detail = new HashMap<>();
        ArrayList participant=new ArrayList<>();
        ArrayList description=new ArrayList<>();
        List<MeetingMember> meetingMembers=meetingMemberRepository.findMeetingMemberByMeetingRoomIdANDStart(meetingRoomId, start);

        meetingMembers.stream()
                .map(meetingMember -> participant.add(meetingMember.getMember().getName()));

        description.add(meetingMembers.get(0).getMeeting().getDescription());

        detail.put("participant",participant);
        detail.put("description",description);


    }

}

