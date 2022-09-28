package com.example.sdpBackEnd.excetion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final StatusEnum statusEnum;

}