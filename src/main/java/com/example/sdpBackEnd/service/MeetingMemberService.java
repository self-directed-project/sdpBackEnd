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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    private final MemberRepository memberRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    //나의 미팅 조회 리스트로 받아오기
    public List<MeetingMemberDto>findMyMeeting(Long memberId){
        List<MeetingMemberDto> myMeetings =
        meetingMemberRepository.findMeetingMemberByMemberId(memberId).stream()
                .map(meetingmember-> MeetingMemberDto.builder()
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

//        if (myMeetings.isEmpty()) {
//            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
//        }
        return myMeetings;
    }

    //나의 미팅 조회 (페이징처리)
    public Page<MeetingMemberDto> p_FindMyMeeting(List<MeetingMemberDto> myMeetings, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), myMeetings.size());

        if(start > myMeetings.size())
            return new PageImpl<>(new ArrayList<>(), pageable, myMeetings.size());

        return new PageImpl<>(myMeetings.subList(start, end), pageable, myMeetings.size());
    }
}

