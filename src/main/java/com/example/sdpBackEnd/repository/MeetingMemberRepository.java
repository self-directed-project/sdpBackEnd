package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.entity.MeetingRoomFav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {

    List<MeetingMember> findMeetingMemberByMemberId(Long memberId);


    List<MeetingMember> findByMeetingId(long meetingId);

    List<MeetingMember> findAllByMeetingIdIn(List<Long> meetingId);
    
}
