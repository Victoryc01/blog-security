package com.samjayworldwide.blogTaskWithSecurity.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
public class InvalidEmailException extends RuntimeException {
    private String errorMessage;
    private HttpStatus httpStatus;
    public InvalidEmailException(String errorMessage,HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
