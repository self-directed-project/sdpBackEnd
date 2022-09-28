package com.example.sdpBackEnd.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavRoomDto {

    private Long id;

    private String name;
}
