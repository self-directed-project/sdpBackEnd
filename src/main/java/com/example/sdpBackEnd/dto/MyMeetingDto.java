package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Member;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyMeetingDto {
    private List<Member> members;
}
