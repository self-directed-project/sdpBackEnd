package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.CalendarMeetingDto;
import com.example.sdpBackEnd.dto.MeetingMemberDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
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
    private final MeetingRoomRepository meetingRoomRepository;

    //미팅 전체 조회
//    public List<Meeting> findAllMeetings() {
//        List<Meeting> meetings = new ArrayList<>();
//        meetingRepository.findAll().forEach(meetings::add);
//        return meetings;
//    }

    //전체 미팅 리스트 조회
    public List<MeetingMemberDto> findAll() {
//        Page<Meeting> p_meetings = meetingRepository.findAllByOrderByStartDesc(pageable);
        List<MeetingMemberDto> meetings =
                meetingRepository.findAllByOrderByStartDesc().stream()
                        .map(meeting-> MeetingMemberDto.builder()
                                .meetingId(meeting.getId())
                                .name(meeting.getName())
                                .start(meeting.getStart())
                                .end(meeting.getEnd())
                                .meetingRoomId(meeting.getMeetingRoom().getId())
                                .meetingRoomName(meetingRoomRepository.findById(meeting.getMeetingRoom().getId()).get().getName())
                                .createdBy(memberRepository.findById(meeting.getCreatedBy()).get().getName())
                                .type(meeting.getMeetingType().toString())
                                .build()).collect(Collectors.toList());
//        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

//        if (meetings.isEmpty()) {
//            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
//        }
        return meetings;
    }

    //전체 미팅 조회 (페이징처리)
    public Page<MeetingMemberDto> p_FindAll(List<MeetingMemberDto> meetings, Pageable pageable) {
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), meetings.size());

//        if (meetings.isEmpty()) {
//        throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
//    }

        if(start > meetings.size())
            return new PageImpl<>(new ArrayList<>(), pageable, meetings.size());

        return new PageImpl<>(meetings.subList(start, end), pageable, meetings.size());
    }


    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    public List<CalendarMeetingDto> findMeetingCalendar(CalendarMeetingDto calendarMeetingDto){
        List<CalendarMeetingDto> calendarMeetings =
                meetingRepository.findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(calendarMeetingDto.getStart(), calendarMeetingDto.getEnd(), calendarMeetingDto.getMeetingRoomId()).stream()
                                .map(meeting -> CalendarMeetingDto.builder()
                                        .meetingRoomId(meeting.getMeetingRoom().getId())
                                        .start(meeting.getStart())
                                        .end(meeting.getEnd())
                                        .type(meeting.getMeetingType().toString())
                                        .name(meeting.getName())
                                        .build()).collect(Collectors.toList());
        if (calendarMeetings.isEmpty()) {
            throw new CustomException(StatusEnum.MEETING_DOES_NOT_EXIST);
        }
        return calendarMeetings;
    }

}