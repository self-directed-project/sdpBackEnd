package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where username = :username and password = :password")
    Member findMember(@Param("username") String username, @Param("password") String password);
}
