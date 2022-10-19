package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MeetingDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private MeetingRoom meetingRoom;

    //예약된 회의실 있는지 유무 체크
    public boolean meetingCheck(MeetingDto meetingDto){
        meetingCheck(meetingDto);
        return meetingRepository.existsByMeetingRoom(meetingRoom);
    }

    // 예약된 회의실 조회
    public List<Meeting> findAllMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findAll().forEach(meetings::add);
        return meetings;
    }

    // 기간에 따라 예약된 회의실 조회
    public List<Meeting> findMeetingInPeriod(LocalDateTime start, LocalDateTime end) {
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findAllByStartGreaterThanEqualAndEndLessThanEqual(start, end).forEach(meetings::add);
        return meetings;
    }

    public List<Meeting> findMeetingInRoom(LocalDateTime start, LocalDateTime end, long id){
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(start,end,id).forEach(meetings::add);
        return meetings;
    }
}
