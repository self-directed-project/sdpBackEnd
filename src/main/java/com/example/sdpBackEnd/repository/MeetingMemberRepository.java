package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.entity.MeetingRoomFav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {


    List<MeetingMember> findByMemberId(Long memberId);
}
