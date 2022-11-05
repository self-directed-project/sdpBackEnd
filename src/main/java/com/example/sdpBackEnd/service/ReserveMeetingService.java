package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingRoomDto;
import com.example.sdpBackEnd.dto.ReserveDto;
import com.example.sdpBackEnd.entity.*;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveMeetingService {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    private final MeetingMemberRepository meetingMemberRepository;


    public void makeMeeting(ReserveDto reserveDto){
        if(!checkTime(reserveDto)){
            throw new RuntimeException("다른 회의 시간과 중복됩니다.");
        }
        else{
            Meeting meeting = Meeting.builder()
                    .name(reserveDto.getName())
                    .meetingRoom(meetingRoomRepository.findById(reserveDto.getMeetingRoomId()).get())
                    .meetingType(reserveDto.getType())
                    .start(reserveDto.getStart())
                    .end(reserveDto.getEnd())
                    .description(reserveDto.getDescription())
                    .build();

            meetingRepository.save(meeting);

            for(int i=0; i<reserveDto.getMembersId().size(); i++){
                MeetingMember meetingMember = MeetingMember.builder()
                        .member(memberRepository.findById(reserveDto.getMembersId().get(i)).get())
                        .meeting(meetingRepository.findByStart(reserveDto.getStart()).get())
                        .build();

                meetingMemberRepository.save(meetingMember);
            }
        }
    }

    //회의 시간이 겹치는지 확인
    public Boolean checkTime(ReserveDto reserveDto){
        int j = 0;

        List<LocalDateTime> startMeetings = meetingRepository.findByMeetingRoom(meetingRoomRepository.findById(reserveDto.getMeetingRoomId()).get()).stream()
                .map(Meeting::getStart).toList();
        List<LocalDateTime> endMeetings = meetingRepository.findByMeetingRoom(meetingRoomRepository.findById(reserveDto.getMeetingRoomId()).get()).stream()
                .map(Meeting::getEnd).toList();

        LocalDateTime start = reserveDto.getStart();
        LocalDateTime end = reserveDto.getEnd();

        for(int i=0; i<startMeetings.size(); i++){
            if(startMeetings.get(i).isAfter(start) && startMeetings.get(i).isAfter(end) && endMeetings.get(i).isBefore(start) && endMeetings.get(i).isBefore(end))
                ;
            else
                j++;
        }

        if(j!=0)
            return false;
        else
            return true;
    }


    //존재하는 모든 회의실의 아이디와 이름 반환
    public List<MeetingRoomDto> allMeetingRoom(){
        List<MeetingRoom> allMeetingRooms = meetingRoomRepository.findAll();

        List<MeetingRoomDto> meetingRoomList = allMeetingRooms.stream()
                .map(meetingRoom -> new MeetingRoomDto(meetingRoom.getId(), meetingRoom.getName()))
                .sorted(Comparator.comparing(MeetingRoomDto::getId))
                .collect(Collectors.toList());

        return meetingRoomList;
    }


    //검색된 이름을 포함하는 member 리스트 반환
}
