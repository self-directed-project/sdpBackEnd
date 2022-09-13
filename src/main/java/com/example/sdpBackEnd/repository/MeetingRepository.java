package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
