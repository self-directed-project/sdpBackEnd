package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Team;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchMemberDto {

    private long id;

    private String name;

    private String username;

    private String team;

}
