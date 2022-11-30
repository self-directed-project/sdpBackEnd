package com.example.sdpBackEnd.excetion;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {


    //200 OK : 요청 성공
    OK(HttpStatus.OK, "로그인 성공"),
    Logout_OK(HttpStatus.OK,"로그아웃 성공"),
    SESSION_OK(HttpStatus.OK, "세션조회 성공"),

    MEETING_ALL_OK(HttpStatus.OK,"모든 미팅 조회 성공"),
    MEETING_My_OK(HttpStatus.OK,"나의 미팅 조회 성공")
    ,
    MEETING_ALLPeriod_OK(HttpStatus.OK,"미팅기간에 따른 조회 성공"),

    MEETING_Calendar_OK(HttpStatus.OK,"미팅기간과 회의실 종류 따른 조회 성공"),

    // 400 BAD_REQUEST: 잘못된 요청
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
  /*  BAD_REQUEST_Session_DOES_NOT_EXIST(HttpStatus.BAD_REQUEST, "브라우저에서 받은 세션이 없습니다."),*/

    BAD_REQUEST_RESERVE_TIME(HttpStatus.BAD_REQUEST, "끝나는 시간이 시작 시간보다 빠릅니다."),

    BAD_REQUEST_OVERLAP_TIME(HttpStatus.BAD_REQUEST, "다른 회의 시간과 중복됩니다."),

    // 403 Forbidden : 콘텐츠에 접근할 권리가 없음
    DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다."),

    UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "수정 권한이 없습니다."),

    // 404 NOT_FOUND: 리소스를 찾을 수 없음
    POSTS_NOT_FOUND_ID(HttpStatus.NOT_FOUND, "회원 아이디가 없습니다."),
    POSTS_NOT_FOUND_PW(HttpStatus.NOT_FOUND, "회원 비밀번호가 일치하지않습니다."),
    GET_NOT_FOUND_SID(HttpStatus.NOT_FOUND, "세션정보를 찾을 수 없습니다."),

    MEETING_DOES_NOT_EXIST(HttpStatus.NOT_FOUND,"미팅이 존재하지 않습니다."),

    // 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다.");


    private final HttpStatus status;
    private final String message;

}