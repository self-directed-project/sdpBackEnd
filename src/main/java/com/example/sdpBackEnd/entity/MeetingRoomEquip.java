package com.example.sdpBackEnd.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="meetingRoomEquip")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MeetingRoomEquip extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meetingRoomId", nullable = false)
    private MeetingRoom meetingRoom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private Equipment equipment;

    @Column(nullable = false)
    int count;
}
