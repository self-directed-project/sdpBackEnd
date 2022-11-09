package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    //미팅 전체 조회
//    public List<Meeting> findAllMeetings() {
//        List<Meeting> meetings = new ArrayList<>();
//        meetingRepository.findAll().forEach(meetings::add);
//        return meetings;
//    }

    //미팅 전체 조회 (페이징처리)
    public Page<MeetingMemberDto> findAll(Pageable pageable) {
        List<MeetingMemberDto> meetings =
                meetingRepository.findAllByOrderByStartDesc(pageable).stream()
                        .map(meeting-> MeetingMemberDto.builder()
                                .name(meeting.getName())
                                .start(meeting.getStart())
                                .end(meeting.getEnd())
                                .meetingRoomId(meeting.getMeetingRoom().getId())
                                .createdBy(memberRepository.findById(meeting.getCreatedBy()).get().getName())
                                .type(meeting.getMeetingType().toString())
                                .build()
                        ).collect(Collectors.toList());

        if (meetings.isEmpty()) {
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
        return  new PageImpl<>(meetings);
    }



    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    public List<Meeting> findMeetingInRoom(LocalDateTime start, LocalDateTime end, long id){
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(start,end,id).forEach(meetings::add);
        return meetings;
    }


}