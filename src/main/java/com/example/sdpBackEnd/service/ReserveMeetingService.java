package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MeetingRoomDto;
import com.example.sdpBackEnd.dto.ReserveDto;
import com.example.sdpBackEnd.dto.SearchMemberDto;
import com.example.sdpBackEnd.dto.UpdateMeetingDto;
import com.example.sdpBackEnd.entity.*;
import com.example.sdpBackEnd.repository.MeetingMemberRepository;
import com.example.sdpBackEnd.repository.MeetingRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveMeetingService {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
    private final MeetingMemberRepository meetingMemberRepository;


    //reserveDto 값으로 회의 생성 및 회의 참여자 생성
    @Transactional
    public void makeMeeting(ReserveDto reserveDto){
        if(!checkStart(reserveDto)){
            throw new RuntimeException("끝나는 시간이 시작 시간보다 빠릅니다.");
        }
        else if(!checkTime(reserveDto)){
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
    @Transactional
    public Boolean checkTime(ReserveDto reserveDto){
        int j = 0;

        List<LocalDateTime> startMeetings = meetingRepository.findByMeetingRoom(meetingRoomRepository.findById(reserveDto.getMeetingRoomId()).get()).stream()
                .map(Meeting::getStart).toList();
        List<LocalDateTime> endMeetings = meetingRepository.findByMeetingRoom(meetingRoomRepository.findById(reserveDto.getMeetingRoomId()).get()).stream()
                .map(Meeting::getEnd).toList();

        LocalDateTime start = reserveDto.getStart().plusMinutes(1);
        LocalDateTime end = reserveDto.getEnd().minusMinutes(1);

        for(int i=0; i<startMeetings.size(); i++){
            if((startMeetings.get(i).isAfter(start)&&endMeetings.get(i).isBefore(start))||(startMeetings.get(i).isAfter(end)&&endMeetings.get(i).isBefore(end)))
                j++;
            else
                ;
        }

        if(j!=0)
            return false;
        else
            return true;
    }

    //끝 시간이 시작시간보다 빠른지 확인
    @Transactional
    public Boolean checkStart(ReserveDto reserveDto){

        LocalDateTime start = reserveDto.getStart();
        LocalDateTime end = reserveDto.getEnd();

        if(end.isBefore(start))
            return false;
        else
            return true;
    }

    //존재하는 모든 회의실의 아이디와 이름 반환
    @Transactional
    public List<MeetingRoomDto> allMeetingRoom(){
        List<MeetingRoom> allMeetingRooms = meetingRoomRepository.findAll();

        List<MeetingRoomDto> meetingRoomList = allMeetingRooms.stream()
                .map(meetingRoom -> new MeetingRoomDto(meetingRoom.getId(), meetingRoom.getName()))
                .sorted(Comparator.comparing(MeetingRoomDto::getId))
                .collect(Collectors.toList());

        return meetingRoomList;
    }

    //검색된 이름을 포함하는 member 리스트 반환
    @Transactional
    public List<SearchMemberDto> searchMember(String name) {
        List<Member> memberList = memberRepository.findByNameContaining(name);

        List<SearchMemberDto> memberDtoList = memberList.stream()
                .map(member -> new SearchMemberDto(member.getId(), member.getName(), member.getUsername(), member.getTeam().getName()))
                .sorted(Comparator.comparing(SearchMemberDto::getId))
                .collect(Collectors.toList());

        return memberDtoList;
    }

    //존재하는 모든 member 리스트 반환
    @Transactional
    public List<SearchMemberDto> AllMembers(){
        List<Member> memberList = memberRepository.findAll();

        List<SearchMemberDto> memberDtoList = memberList.stream()
                .map(member -> new SearchMemberDto(member.getId(), member.getName(), member.getUsername(), member.getTeam().getName()))
                .sorted(Comparator.comparing(SearchMemberDto::getId))
                .collect(Collectors.toList());

        return memberDtoList;
    }

    //자신이 생성한 사람일 시 회의 삭제
    @Transactional
    public void deleteMeetingList(long memberId, List<Long> meetingsId){

        List<Meeting> meetingList = meetingRepository.findAllByIdIn(meetingsId);
        List<MeetingMember> meetingMembers = meetingMemberRepository.findAllByMeetingIdIn(meetingsId);

        for(int i = 0; i<meetingMembers.size(); i++){
            if(memberId==meetingMembers.get(i).getCreatedBy()){
                meetingMemberRepository.deleteById(meetingMembers.get(i).getId());
            }
            else{
                throw new IllegalArgumentException("삭제 권한이 없습니다.");
            }
        }

        for(int i = 0; i<meetingList.size(); i++){
            if(memberId==meetingList.get(i).getCreatedBy()){
                meetingRepository.deleteById(meetingList.get(i).getId());
            }
            else{
                throw new IllegalArgumentException("삭제 권한이 없습니다.");
            }
        }
    }

    //회의 하나씩 삭제
    @Transactional
    public void deleteMeeting(long memberId, long meetingId){

        Optional<Meeting> meetingList = meetingRepository.findById(meetingId);

        if(meetingList.isPresent()){
            if(memberId==meetingList.get().getCreatedBy()){
                meetingRepository.delete(meetingList.get());
            }
            else{
                throw new IllegalArgumentException("삭제 권한이 없습니다.");
            }
        }
    }

    //회의 수정
    @Transactional
    public void updateMeeting(long memberId,long meetingId, ReserveDto reserveDto){

        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(()-> new IllegalArgumentException("회의가 없습니다."));

        if(meeting.getCreatedBy()!=memberId){
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        else if(!checkStart(reserveDto)){
            throw new RuntimeException("끝나는 시간이 시작 시간보다 빠릅니다.");
        }
        else if(!checkTime(reserveDto)){
            throw new RuntimeException("다른 회의 시간과 중복됩니다.");
        }
        else{
            List<MeetingMember> meetingMembers = meetingMemberRepository.findByMeetingId(meeting.getId());

            for(int j=0; j<meetingMembers.size(); j++){
                meetingMemberRepository.deleteById(meetingMembers.get(j).getId());
            }

            for(int i=0; i<reserveDto.getMembersId().size(); i++){
                MeetingMember meetingMember = MeetingMember.builder()
                        .member(memberRepository.findById(reserveDto.getMembersId().get(i)).get())
                        .meeting(meetingRepository.findById(meeting.getId()).get())
                        .build();

                meetingMemberRepository.save(meetingMember);
            }

            Meeting buildMeeting = Meeting.builder()
                    .id(meetingId)
                    .name(reserveDto.getName())
                    .meetingRoom(meetingRoomRepository.findById(reserveDto.getMeetingRoomId()).get())
                    .meetingType(reserveDto.getType())
                    .start(reserveDto.getStart())
                    .end(reserveDto.getEnd())
                    .description(reserveDto.getDescription())
                    .build();

            meetingRepository.save(buildMeeting);
        }
    }

    @Transactional
    public List<SearchMemberDto> updateSearchMember(long meetingId){

        List<MeetingMember> meetingMembers = meetingMemberRepository.findByMeetingId(meetingId);

        List<Long> membersId = meetingMembers.stream().map(m -> m.getMember().getId()).collect(Collectors.toList());

        List<Member> memberList = memberRepository.findAllById(membersId);

        List<SearchMemberDto> memberDtoList = memberList.stream()
                .map(member -> new SearchMemberDto(member.getId(), member.getName(), member.getUsername(), member.getTeam().getName()))
                .sorted(Comparator.comparing(SearchMemberDto::getId))
                .collect(Collectors.toList());

        return memberDtoList;
    }

    @Transactional
    public UpdateMeetingDto updateSearchMeeting(long memberId, long meetingId){
        Meeting meeting = meetingRepository.findById(meetingId).get();

        if(meeting.getCreatedBy() != memberId)
            throw new RuntimeException("수정 권한이 없습니다.");
        else{
            UpdateMeetingDto updateMeetingDto = UpdateMeetingDto.builder()
                    .id(meeting.getId())
                    .meetingRoom(meeting.getMeetingRoom().getId())
                    .meetingType(meeting.getMeetingType())
                    .description(meeting.getDescription())
                    .name(meeting.getName())
                    .endDate(meeting.getEnd())
                    .endHour(meeting.getEnd())
                    .endMinute(meeting.getEnd())
                    .startDate(meeting.getStart())
                    .startHour(meeting.getStart())
                    .startMinute(meeting.getStart())
                    .build();

            return updateMeetingDto;
        }
    }
}
