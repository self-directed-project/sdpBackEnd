package com.example.sdpBackEnd.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="meetingRoomFav")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingRoomFav extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meetingRoomId", nullable = false)
    private MeetingRoom meetingRoom;
}
