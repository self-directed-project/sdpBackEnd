package com.example.sdpBackEnd.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeleteMeetingDto {

    private List<Long> meetingsId;
}
