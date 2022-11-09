package com.example.sdpBackEnd.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = true, nullable = false)
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private String createdDate;


    @LastModifiedDate
    @Column(nullable = true)
    @DateTimeFormat(pattern = "yy.MM.dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private String lastModifiedDate;


}
