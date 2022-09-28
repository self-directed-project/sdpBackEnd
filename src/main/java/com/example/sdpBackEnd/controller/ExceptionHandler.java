package com.example.sdpBackEnd.controller;

import com.example.sdpBackEnd.dto.MemberResponseDto;
import com.example.sdpBackEnd.excetion.CustomException;
import com.example.sdpBackEnd.excetion.StatusEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    //customException발생 - 400,404
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    protected ResponseEntity<MemberResponseDto> handleCustomException(final CustomException e) {
        log.error("handleCustomException: {}", e.getStatusEnum());
        return ResponseEntity
                .status(e.getStatusEnum().getStatus().value())
                .body(new MemberResponseDto(e.getStatusEnum()));
    }

    //httpException발생 - 405
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<MemberResponseDto> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage());
        return ResponseEntity
                .status(StatusEnum.METHOD_NOT_ALLOWED.getStatus().value())
                .body(new MemberResponseDto(StatusEnum.METHOD_NOT_ALLOWED));
    }



}
