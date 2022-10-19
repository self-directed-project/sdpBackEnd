package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.ReserveDto;
import com.example.sdpBackEnd.entity.Meeting;
import com.example.sdpBackEnd.entity.MeetingMember;
import com.example.sdpBackEnd.entity.MeetingType;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

            for(int i=0; i<reserveDto.getMembers().size(); i++){
                MeetingMember meetingMember = MeetingMember.builder()
                        .member(reserveDto.getMembers().get(i))
                        .meeting(meetingRepository.findByStart(reserveDto.getStart()).get())
                        .build();

                meetingMemberRepository.save(meetingMember);
            }
        }
    }

    public Boolean checkTime(ReserveDto reserveDto){
        int j = 0;

        List<LocalDateTime> startMeetings = meetingRepository.findAll().stream()
                .map(Meeting::getStart).toList();
        List<LocalDateTime> endMeetings = meetingRepository.findAll().stream()
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

}
