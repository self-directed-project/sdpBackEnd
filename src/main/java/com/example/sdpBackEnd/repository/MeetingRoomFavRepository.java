package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.entity.MeetingRoomFav;
import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRoomFavRepository extends JpaRepository<MeetingRoomFav, Long> {

    Optional<MeetingRoomFav> findMeetingRoomFavByMemberAndMeetingRoom(Member member, MeetingRoom meetingRooms);
    boolean existsByMemberAndMeetingRoom(Member member, MeetingRoom meetingRoom);
}
