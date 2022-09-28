package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUsername(String username);

    Optional<Member> findByUsernameAndPassword(String username, String password);
}
