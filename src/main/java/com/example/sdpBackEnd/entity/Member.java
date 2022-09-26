package com.example.sdpBackEnd.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
}
