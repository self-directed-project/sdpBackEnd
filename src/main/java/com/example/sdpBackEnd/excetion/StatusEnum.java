package com.example.sdpBackEnd.excetion;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {


    //200 OK : 요청 성공
    OK(HttpStatus.OK, "로그인 성공"),
     // 400 BAD_REQUEST: 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    BAD_REQUEST_Session(HttpStatus.BAD_REQUEST, "잘못된 세션 요청."),


     // 404 NOT_FOUND: 리소스를 찾을 수 없음
    POSTS_NOT_FOUND_ID(HttpStatus.NOT_FOUND, "회원 아이디가 없습니다."),
    POSTS_NOT_FOUND_PW(HttpStatus.NOT_FOUND, "회원 비밀번호가 일치하지않습니다."),


     // 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다.");


    private final HttpStatus status;
    private final String message;

}