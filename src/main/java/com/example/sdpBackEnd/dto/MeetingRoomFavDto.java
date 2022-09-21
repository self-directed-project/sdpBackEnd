package com.example.sdpBackEnd.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingRoomFavDto {

    private Long memberId;

    private Long meetingRoomId;


}
