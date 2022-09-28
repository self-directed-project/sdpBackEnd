package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
