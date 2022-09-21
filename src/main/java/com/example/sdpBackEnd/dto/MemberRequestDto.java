package com.example.sdpBackEnd.dto;

import com.example.sdpBackEnd.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor//테스트용
public class MemberRequestDto {


    @NotBlank(message = "id입력은 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;

}
