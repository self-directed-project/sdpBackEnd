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

    Optional<Meeting> findByStart(LocalDateTime start);

    // MeetingRepository
    List<Meeting> findAll();

    List<Meeting> findAllByStartGreaterThanEqualAndEndLessThanEqual(LocalDateTime start, LocalDateTime end);

    List<Meeting> findAllByStartGreaterThanEqualAndEndLessThanEqualAndMeetingRoomIdEquals(LocalDateTime start, LocalDateTime end, long id);



}
