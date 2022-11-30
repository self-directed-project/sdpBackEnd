package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.entity.MeetingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Optional<Meeting> findByStart(LocalDateTime start);

    //미팅 전체 조회
    //List<Meeting> findAll();

    //start 순서별 미팅 전체 조회 (페이징처리)
    List<Meeting> findAllByOrderByStartDesc();

    //미팅 기간 + 회의실 종류에 따라 조회 (캘린더)
    List<Meeting> findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(LocalDateTime start, LocalDateTime end, long id);

    List<Meeting> findByMeetingRoom(MeetingRoom meetingRoom);


    List<Meeting> findAllByIdIn(List<Long> id);
    
    Optional<Meeting> findById(Long id);

}
