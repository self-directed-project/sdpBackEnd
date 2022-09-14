package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {
}
