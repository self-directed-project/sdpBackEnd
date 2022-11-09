package com.example.sdpBackEnd.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingRoomDto {

    private long id;

    private String name;
}
