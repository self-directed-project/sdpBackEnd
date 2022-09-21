package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    //미팅룸 유무
    Boolean existsByMeetingRoom(MeetingRoom meetingRoom);

    //미팅룸 번호에 따라 예약된 미팅 정보 가져오기
    Optional<Meeting> findMeetingByMeetingRoom(MeetingRoom meetingRoom);
}
