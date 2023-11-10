package com.samjayworldwide.blogTaskWithSecurity.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
public class InvalidLoginDetailsException extends RuntimeException {
    private String errorMessage;
    private HttpStatus httpStatus;
    public InvalidLoginDetailsException(String errorMessage,HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
