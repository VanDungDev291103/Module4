package com.techzen.academy.controller;

import com.techzen.academy.Employee;
import com.techzen.academy.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class employeeManagementController {
    private List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1,"dũng", Employee.Gender.MALE,180000, "0387161032"),
                    new Employee(2,"LY", Employee.Gender.FEMALE,200000, "0987777777"),
                    new Employee(3,"Hai", Employee.Gender.MALE,30000, "03873333333"),
                    new Employee(4,"Ân", Employee.Gender.FEMALE,180000, "0387161111"),
                    new Employee(5,"Dorran", Employee.Gender.MALE,5000000, "0387555555")
            )
    );

//    @GetMapping
////    public ResponseEntity<List<Employee>> getAll() {
////        return ResponseEntity.status(HttpStatus.OK).body(employees);
////    }
////
////    @GetMapping("{id}")
////    public ResponseEntity<Employee> getById(@RequestParam("id") int id) {
////        return employees.stream().filter(e -> e.getId() == id).findFirst()
////                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
////    }
////
////    @PostMapping
////    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
////        employee.setId((int) (Math.random()*10));
////        employees.add(employee);
////        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
////    }
////
////
////    @PutMapping("/{id}")
////    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
////        return employees.stream().filter(e -> e.getId() == id).findFirst()
////                .map(e -> {
////                    e.setName(employee.getName());
////                    e.setGender(employee.getGender());
////                    e.setSalary(employee.getSalary());
////                    e.setPhone(employee.getPhone()); // Sửa đổi này để cập nhật thuộc tính phone
////                    return ResponseEntity.ok(e);
////                })
////                .orElseGet(() -> ResponseEntity.notFound().build());
////    }
        public ResponseEntity<ApiResponse<?>> getAll() {
            return ResponseEntity.ok().body(ApiResponse.builder().data(employees).build());
        }

        @GetMapping("{id}")
        public ResponseEntity<ApiResponse<Employee>> getById(@RequestParam("id") int id) {
            for (Employee e : employees) {
                if (e.getId() == id) {

                }
            }
            return
        }
}
