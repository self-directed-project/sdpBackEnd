package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.FavRoomDto;
import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.entity.*;
import com.example.sdpBackEnd.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingRoomFavService {

    private final MeetingRoomFavRepository meetingRoomFavRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final MemberRepository memberRepository;
    private Member member;
    private MeetingRoom meetingRoom;

    // 회의실 즐겨찾기 테이블이 존재하는지 확인하는
    @Transactional
    public boolean favCheck(MeetingRoomFavDto favDto){
        checkMemberAndMeetingRoom(favDto);
        return meetingRoomFavRepository.existsByMemberAndMeetingRoom(member, meetingRoom);
    }

    // 회의실 즐겨찾기 테이블의 아이디를 가져오는
    @Transactional
    public long getFav(MeetingRoomFavDto favDto){

        checkMemberAndMeetingRoom(favDto);

        Optional<MeetingRoomFav> fav = meetingRoomFavRepository.
                findMeetingRoomFavByMemberAndMeetingRoom(member, meetingRoom);

        return fav.get().getId();
    }

    // 회의실 즐겨찾기 테이블의 유무에 따라 테이블을 저장하거나 삭제하는
    @Transactional
    public void favPost(MeetingRoomFavDto favDto){
        if(!favCheck(favDto)){
            MeetingRoomFav meetingRoomFav = MeetingRoomFav.builder()
                    .member(memberRepository.findById(favDto.getMemberId()).get())
                    .meetingRoom(meetingRoomRepository.findById(favDto.getMeetingRoomId()).get())
                    .build();
            meetingRoomFavRepository.save(meetingRoomFav);
        }
        else{
            long favId = getFav(favDto);
            MeetingRoomFav meetingRoomFav = meetingRoomFavRepository.findById(favId).get();
            meetingRoomFavRepository.delete(meetingRoomFav);
        }
    }

    //멤버와 회의실의 id를 통해 객체를 만들어주는
    @Transactional
    public void checkMemberAndMeetingRoom(MeetingRoomFavDto favDto){
        Optional<Member> memberOptional = memberRepository.findById(favDto.getMemberId());
        Optional<MeetingRoom> meetingRoomOptional = meetingRoomRepository.findById((favDto.getMeetingRoomId()));
        if(memberOptional.isEmpty()){
            throw new IllegalArgumentException("해당 회원은 없습니다.");
        }
        else{
            member = memberOptional.get();
        }

        if(meetingRoomOptional.isEmpty()){
            throw new IllegalArgumentException("해당 회의실은 없습니다.");
        }
        else{
            meetingRoom = meetingRoomOptional.get();
        }
    }

    //즐겨찾기 한 회의실들을 FavRoomDto 형식으로 리스트 반환
    @Transactional
    public List<FavRoomDto> getFavMeetingRooms(MeetingRoomFavDto favDto){

        List<FavRoomDto> favDtoList = meetingRoomFavRepository.findByMemberId(favDto.getMemberId()).stream()
                .map(meetingRoomFav -> new FavRoomDto(meetingRoomFav.getMeetingRoom().getId(), meetingRoomRepository.findById(meetingRoomFav.getMeetingRoom().getId()).get().getName()))
                .sorted(Comparator.comparing(FavRoomDto::getId))
                .collect(Collectors.toList());

        return favDtoList;
    }

    //즐겨찾기가 아닌 회의실을 리스트로 반환
    @Transactional
    public List<FavRoomDto> getNonFavMeetingRooms(MeetingRoomFavDto favDto){

        List<MeetingRoom> allMeetingRooms = meetingRoomRepository.findAll();
        List<MeetingRoomFav> favList = meetingRoomFavRepository.findByMemberId(favDto.getMemberId());

        for(int i = 0; i< favList.size(); i++){
            MeetingRoom favRoom = meetingRoomRepository.findById(favList.get(i).getMeetingRoom().getId()).get();
            if(allMeetingRooms.contains(favRoom)){
                allMeetingRooms.remove(favRoom);
            }
        }

        List<FavRoomDto> nonFavDtoList = allMeetingRooms.stream()
                .map(meetingRoom -> new FavRoomDto(meetingRoom.getId(), meetingRoom.getName()))
                .sorted(Comparator.comparing(FavRoomDto::getId))
                .collect(Collectors.toList());

        return nonFavDtoList;
    }
}
