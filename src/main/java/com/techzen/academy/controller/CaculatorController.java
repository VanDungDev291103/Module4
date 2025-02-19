package com.techzen.academy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaculatorController {
    @GetMapping("/caculator")
    public ResponseEntity<String> caculator(
            @RequestParam(defaultValue = " ") String firstNummber,
            @RequestParam(defaultValue = "") String secondNumber,
            @RequestParam(defaultValue = "") String operator)
    {
        //kiểm tra yêu cầu nhập đầy đủ
        if (firstNummber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First number cannot be empty");
        } else if (secondNumber.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Second number cannot be empty");
        } else if (!isDouble(firstNummber)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First number must be a number");
        } else if (!isDouble(secondNumber)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Second number must be a number");
        }

        //chuyển các String nhập thành double để tính toán
        double firstNum = Double.parseDouble(firstNummber);
        double secondNum = Double.parseDouble(secondNumber);
        double result;

        //xem trường hợp nhập vào operater
        switch (operator) {
            case "+" : result = firstNum + secondNum; break;
            case "-" : result = firstNum - secondNum; break;
            case "*" : result = firstNum * secondNum; break;
            case "/" : {
                if (secondNum == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Division by zero");
                }
                result = firstNum / secondNum;
                break;
            }
            default : {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valid operator");
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("The result is: " + result);
    }

    //
    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
