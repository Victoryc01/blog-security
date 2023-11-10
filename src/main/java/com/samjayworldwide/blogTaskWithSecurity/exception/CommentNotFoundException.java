package com.samjayworldwide.blogTaskWithSecurity.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@NoArgsConstructor
public class CommentNotFoundException extends RuntimeException {
    private String errorMessage;
    private HttpStatus httpStatus;
    public CommentNotFoundException(String errorMessage,HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
