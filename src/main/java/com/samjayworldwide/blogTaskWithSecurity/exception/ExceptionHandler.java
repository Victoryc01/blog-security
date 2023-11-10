package com.samjayworldwide.blogTaskWithSecurity.exception;

import com.samjayworldwide.blogTaskWithSecurity.dto.response.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidEmailException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidEmailException(InvalidEmailException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("type in a new email")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidPasswordTypeException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidPasswordTypeException(InvalidPasswordTypeException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("try typing a password that match")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = PostNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlePostNotFoundException(PostNotFoundException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("try searching for comments in another post")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CommentNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleCommentNotFoundException(CommentNotFoundException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("comment successfully deleted")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidNumberOfLikeException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidNumberOfLikeException(InvalidNumberOfLikeException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("proceed to like again")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }




    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFoundException(UserNotFoundException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("try searching for a valid user")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }



    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidLoginDetailsException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidLoginDetailsException(InvalidLoginDetailsException exception){
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(exception.getErrorMessage())
                .responseStatus(false)
                .responseData("try input a valid log in credentials")
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Object>> handleGlobalExceptions(MethodArgumentNotValidException exception,
                                                                         WebRequest request){
        String[] errors = exception.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
        ApiResponse<Object> response = ApiResponse
                .builder()
                .responseMessage(Arrays.toString(errors))
                .responseStatus(false)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
