package com.techzen.academy.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode{

    STUDENT_NOT_EXIST(40401,"Student is not exist",HttpStatus.NOT_FOUND),
    TEACHER_NOT_EXIST(40402,"TEACHER is not exist",HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus httpStatus;
}
