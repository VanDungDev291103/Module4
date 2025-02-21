package com.techzen.academy.controller;

import com.techzen.academy.model.Student;
import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.exception.ApiException;
import com.techzen.academy.exception.ErrorCode;
import com.techzen.academy.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/students")
//@Scope("singleton") - để ý 2 loại scope là singleton và prototype (có đầy đủ 5 loại) // ctrl + space để hiện
// không nên để Scope - chỉ để khi cần thiết
//@AllArgsConstructor // cũng không nên dùng . nên dùng  @RequiredArgsConstructor
@RequiredArgsConstructor // cái nào final thì sẽ tiêm
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class StudentController {
    IStudentService studentService;
    // Có 3 cách tiêm : cách 1 tiêm theo thuộc tính
    //cách 2 : tiêm theo constructor (nên dùng)

    // cách 3: tiêm theo setter
    // lưu ý: cách 1 và 3 nếu nhiều thuộc tính thì sẽ phải tạo nhiều trường hoặc setter nhiều lần
//    @Autowired
//    final IStudentService studentService;

//    public StudentController(IStudentService studentService) {
//        this.studentService = studentService;
//    } // sử dụng @AllArgsConstructor

    //@RequestMapping(value = "/Students", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<?> getStudents(@RequestParam(defaultValue = "") String name) {
        //return ResponseEntity.status(200).body(studentSearch);HƠI THÔ NÊN SÀI CACHS DƯỚI
        //return ResponseEntity.status(HttpStatus.OK).body(studentSearch);hoặc
        return  ResponseEntity.ok(ApiResponse.builder().data(studentService.findByName(name)).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable("id") int id) {
//        return  ResponseEntity.status(ErrorCode.STUDENT_NOT_EXIST.getHttpStatus()).body(ApiResponse.<Student>builder()
//                .code(ErrorCode.STUDENT_NOT_EXIST.getCode()).build());
        //hoặc theo kiểu này return ResponseEntity.ok(ApiResponse.<Student>builder().data(students).build());
        Student student = studentService.findById(id);
        if (student == null) {
            throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
        }

        return ResponseEntity.ok(ApiResponse.<Student>builder().data(student).build());
    }

    //@RequestMapping(value = "/Students", method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> save(@RequestBody Student students) {

        //return ResponseEntity.status(201).body(students); HƠI THÔ NÊN SÀI CACHS DƯỚI
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Student>builder().data(studentService.save(students)).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable("id") int id, @RequestBody Student student) {

        student.setId(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Student>builder()
                .data(studentService.save(student)).build());
    }

}
