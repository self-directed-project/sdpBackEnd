package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.entity.MeetingRoomFav;
import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRoomFavRepository extends JpaRepository<MeetingRoomFav, Long> {

    Optional<MeetingRoomFav> findMeetingRoomFavByMemberAndMeetingRoom(Member member, MeetingRoom meetingRooms);

    boolean existsByMemberAndMeetingRoom(Member member, MeetingRoom meetingRoom);

    /*Page<MeetingRoomFav> findByMemberId(Long memberId, Pageable pageable);*/

    List<MeetingRoomFav> findByMemberId(Long memberId);
}
