package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
@RequiredArgsConstructor
public class MeetingMemberService {

    private final MeetingMemberRepository meetingMemberRepository;

    public List<MeetingMemberDto>findMyMeeting(Long memberId){

        List<MeetingMemberDto> meetings =
        meetingMemberRepository.findMeetingMemberByMemberId(memberId).stream()
                .map(meetingmember-> MeetingMemberDto.builder()
                        .name(meetingmember.getMeeting().getName())
                        .start(meetingmember.getMeeting().getStart())
                        .end(meetingmember.getMeeting().getEnd())
                        .meetingRoomId(meetingmember.getMeeting().getMeetingRoom().getId())
                        .createdBy(meetingmember.getMeeting().getCreatedBy())
                        .build()
                ).collect(Collectors.toList());

        if (meetings.isEmpty()) {
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
        return meetings;
    }



}

