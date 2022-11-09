package com.example.sdpBackEnd.repository;

import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {



}