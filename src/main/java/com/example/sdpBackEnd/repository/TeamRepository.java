package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
