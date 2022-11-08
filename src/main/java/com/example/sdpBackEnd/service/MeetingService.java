package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {
    private final MeetingRepository meetingRepository;

    //미팅 전체 조회
//    public List<Meeting> findAllMeetings() {
//        List<Meeting> meetings = new ArrayList<>();
//        meetingRepository.findAll().forEach(meetings::add);
//        return meetings;
//    }

    //미팅 전체 조회 (페이징처리)
    public Page<Meeting> _findAll(Pageable pageable) {
        return meetingRepository.findAllByOrderByStartDesc(pageable);
    }

    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    public List<Meeting> findMeetingInRoom(LocalDateTime start, LocalDateTime end, long id){
        List<Meeting> meetings = new ArrayList<>();
        meetingRepository.findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(start,end,id).forEach(meetings::add);
        return meetings;
    }


}
