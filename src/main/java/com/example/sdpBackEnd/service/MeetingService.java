package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.Domain.MeetingDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    //예약된 회의실 조회
    public Optional<Meeting> findMeetingByMeetingRoom(Long meetingRoomId){
        Optional<Meeting> meetings = meetingRepository.findMeetingByMeetingRoom(meetingRoom);
        return meetings;
    }
}
