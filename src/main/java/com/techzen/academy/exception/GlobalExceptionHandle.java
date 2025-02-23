package com.techzen.academy.exception;

import com.techzen.academy.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleException(Exception e) {
        //ErrorCode errorCode = e.getErrorCode();

        return  ResponseEntity.status(ErrorCode.STUDENT_NOT_EXIST.getStatusCode()).body(ApiResponse.builder()
                .code(ErrorCode.STUDENT_NOT_EXIST.getCode())
                .message(ErrorCode.STUDENT_NOT_EXIST.getMessage())
                .build());
    }
}
