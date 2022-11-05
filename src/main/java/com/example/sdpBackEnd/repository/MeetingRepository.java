package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    //미팅룸 유무
    Boolean existsByMeetingRoom(MeetingRoom meetingRoom);

    //미팅룸 번호에 따라 예약된 미팅 정보 가져오기
    Optional<Meeting> findByMeetingRoom(MeetingRoom meetingRoom);

    Optional<Meeting> findByStart(LocalDateTime start);

    // MeetingRepository
    List<Meeting> findAll();

    List<Meeting> findAllByStartGreaterThanEqualAndEndLessThanEqual(LocalDateTime start, LocalDateTime end);

    List<Meeting> findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(LocalDateTime start, LocalDateTime end, long id);

}
