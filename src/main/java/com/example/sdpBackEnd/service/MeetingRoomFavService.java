package com.example.sdpBackEnd.service;

import com.example.sdpBackEnd.dto.MeetingRoomFavDto;
import com.example.sdpBackEnd.entity.MeetingRoom;
import com.example.sdpBackEnd.entity.MeetingRoomFav;
import com.example.sdpBackEnd.entity.Member;
import com.example.sdpBackEnd.repository.MeetingRoomFavRepository;
import com.example.sdpBackEnd.repository.MeetingRoomRepository;
import com.example.sdpBackEnd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingRoomFavService {

    private final MeetingRoomFavRepository meetingRoomFavRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final MemberRepository memberRepository;
    private Member member;
    private MeetingRoom meetingRoom;

    // 회의실 즐겨찾기 테이블이 존재하는지 확인하는
    public boolean favCheck(MeetingRoomFavDto favDto){
        checkMemberAndMeetingRoom(favDto);
        return meetingRoomFavRepository.existsByMemberAndMeetingRoom(member, meetingRoom);
    }

    // 회의실 즐겨찾기 테이블의 아이디를 가져오는
    public long getFav(MeetingRoomFavDto favDto){

        checkMemberAndMeetingRoom(favDto);

        Optional<MeetingRoomFav> fav = meetingRoomFavRepository.
                findMeetingRoomFavByMemberAndMeetingRoom(member, meetingRoom);

        return fav.get().getId();
    }

    // 회의실 즐겨찾기 테이블의 유무에 따라 테이블을 저장하거나 삭제하는
    public void favPost(MeetingRoomFavDto favDto){
        long favId = getFav(favDto);

        if(!favCheck(favDto)){
            MeetingRoomFav meetingRoomFav = MeetingRoomFav.builder()
                    .member(memberRepository.findById(favDto.getMemberId()).get())
                    .meetingRoom(meetingRoomRepository.findById(favDto.getMeetingRoomId()).get())
                    .build();
            meetingRoomFavRepository.save(meetingRoomFav);
        }
        else{
            MeetingRoomFav meetingRoomFav = meetingRoomFavRepository.findById(favId).get();
            meetingRoomFavRepository.delete(meetingRoomFav);
        }

    }

    //멤버와 회의실의 id를 통해 객체를 만들어주는
    public void checkMemberAndMeetingRoom(MeetingRoomFavDto favDto){
        Optional<Member> memberOptional = memberRepository.findById(favDto.getMemberId());
        Optional<MeetingRoom> meetingRoomOptional = meetingRoomRepository.findById((favDto.getMeetingRoomId()));
        if(memberOptional.isEmpty()){
            //throw new
        }
        else{
            member = memberOptional.get();
        }

        if(meetingRoomOptional.isEmpty()){
            //throw new
        }
        else{
            meetingRoom = meetingRoomOptional.get();
        }

    }
}
