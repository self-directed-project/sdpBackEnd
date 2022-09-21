package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


   boolean existsByUsername(String username);

    Optional<Member> findByUsernameAndPassword(String username,String password);
}
