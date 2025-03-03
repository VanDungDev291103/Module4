package com.techzen.academy.controller;

import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.service.IDepartmentService;
import com.techzen.academy.util.JsonResponse;
import com.techzen.academy.exception.AppException;
import com.techzen.academy.exception.ErrorCode;
import com.techzen.academy.model.Department;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/departments")
public class DepartmentController {

    IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Department> departments = departmentService.findAll();
        if (departments == null || departments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return departmentService.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
//        return  departments.stream().filter(d -> d.getId() == id)
//                .findFirst()
//                .map(JsonResponse::ok)
//                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
//    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        return JsonResponse.created(departmentService.save(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Department department) {
        departmentService.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        department.setId(id);
        return JsonResponse.ok(departmentService.save(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        departmentService.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        departmentService.delete(id);
        return JsonResponse.noContent();
    }
}
