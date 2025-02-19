package com.techzen.academy.controller;

import com.techzen.academy.Student;
import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.exception.ApiException;
import com.techzen.academy.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/Students")
public class StudentController {
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Dũng", 4.5),
            new Student(2, "Doran", 8),
            new Student(3, "hello", 3)
    )
    );

    //@RequestMapping(value = "/Students", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name) {
        List<Student> studentSearch = new ArrayList<>();

        for (Student students : students) {
            if (students.getName().contains(name)) { // like
                studentSearch.add(students);
            }
        }
        //return ResponseEntity.status(200).body(studentSearch);HƠI THÔ NÊN SÀI CACHS DƯỚI
        //return ResponseEntity.status(HttpStatus.OK).body(studentSearch);hoặc
        return  ResponseEntity.ok(ApiResponse.builder().data(studentSearch).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {

        for (Student students : students) {
            if (students.getId() == id) { // like
                //return ResponseEntity.ok(new ApiResponse<>(null,null, students));
                //hoặc
                return ResponseEntity.ok(ApiResponse.<Student>builder().data(students).build());
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
//        return  ResponseEntity.status(ErrorCode.STUDENT_NOT_EXIST.getHttpStatus()).body(ApiResponse.<Student>builder()
//                .code(ErrorCode.STUDENT_NOT_EXIST.getCode()).build());
        //hoặc theo kiểu này return ResponseEntity.ok(ApiResponse.<Student>builder().data(students).build());
    }

    //@RequestMapping(value = "/Students", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student students) {
        students.setId((int) (Math.random() * 100));
        this.students.add(students);

        //return ResponseEntity.status(201).body(students); HƠI THÔ NÊN SÀI CACHS DƯỚI
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Student>builder().data(students).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") int id, @RequestBody Student student) {

        for (Student std : this.students) {
            if (std.getId() == id) {
                //std.setId(id);
                std.setName(std.getName());
                std.setScore(std.getScore());
                return  ResponseEntity.ok(std);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        //return ResponseEntity.notFound().build();
    }

}
