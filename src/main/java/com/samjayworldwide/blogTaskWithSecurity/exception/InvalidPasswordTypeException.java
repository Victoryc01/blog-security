package com.samjayworldwide.blogTaskWithSecurity.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
public class InvalidPasswordTypeException extends RuntimeException {
    private String errorMessage;
    private HttpStatus httpStatus;
    public InvalidPasswordTypeException(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
