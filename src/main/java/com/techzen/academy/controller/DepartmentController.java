package com.techzen.academy.controller;

import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.dto.JsonResponse;
import com.techzen.academy.exception.AppException;
import com.techzen.academy.exception.ErrorCode;
import com.techzen.academy.model.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>(Arrays.asList(new Department(1, "kế toán"), new Department(2, "marketing"), new Department(3, "Quản lý")));

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.builder().data(departments).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        for (Department d : departments) {
            if (d.getId().equals(id)) {
                return ResponseEntity.ok(JsonResponse.ok(d));
            }
        }
        throw new AppException(ErrorCode.DEPARTMENT_NOT_EXIST);
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
        department.setId((int) (Math.random() * 10));
        departments.add(department);
        return JsonResponse.created(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Department department) {
        return departments.stream().filter(d -> d.getId() == id).findFirst().map(d -> {
            d.setName(department.getName());
            return JsonResponse.ok(d);
        }).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return departments.stream().filter(d -> d.getId() == id).findFirst().map(d -> {
            departments.remove(d);
            return JsonResponse.noContent();
        }).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXIST));
    }
}
