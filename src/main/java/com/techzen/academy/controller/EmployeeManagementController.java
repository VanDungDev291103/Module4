package com.techzen.academy.controller;

import com.techzen.academy.exception.AppException;
import com.techzen.academy.exception.ErrorCode;
import com.techzen.academy.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeManagementController {
    private List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "dũng", Employee.Gender.MALE, 180000, "0387161032",1),
                    new Employee(2, "LY", Employee.Gender.FEMALE, 200000, "0987777777",2),
                    new Employee(3, "Hai", Employee.Gender.MALE, 30000, "03873333333",3),
                    new Employee(4, "Ân", Employee.Gender.FEMALE, 180000, "0387161111",2),
                    new Employee(5, "Dorran", Employee.Gender.MALE, 5000000, "0387555555",3)
            )
    );

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    // cách ngắn gọn
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }
    // Cách dài dòng
//    @GetMapping("{id}")
//    public ResponseEntity<Employee> getById(@PathVariable("id") int id) {
//        // Chuyển danh sách employees thành Stream
//        Stream<Employee> employeeStream = employees.stream();
//
//        // Lọc danh sách nhân viên theo ID
//        Predicate<Employee> filterById = new Predicate<Employee>() {
//            @Override
//            public boolean test(Employee e) {
//                return e.getId() == id;
//            }
//        };
//        Stream<Employee> filteredStream = employeeStream.filter(filterById);
//
//        // Lấy phần tử đầu tiên tìm thấy
//        Optional<Employee> optionalEmployee = filteredStream.findFirst();
//
//        // Chuyển đổi Optional<Employee> thành ResponseEntity
//        Function<Employee, ResponseEntity<Employee>> toResponseEntity = new Function<Employee, ResponseEntity<Employee>>() {
//            @Override
//            public ResponseEntity<Employee> apply(Employee e) {
//                return ResponseEntity.ok(e);
//            }
//        };
//
//        Optional<ResponseEntity<Employee>> optionalResponseEntity = optionalEmployee.map(toResponseEntity);
//
//        // Trả về giá trị nếu có, nếu không thì trả về 404
//        if (optionalResponseEntity.isPresent()) {
//            return optionalResponseEntity.get();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee.setId((int) (Math.random() * 10));
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    e.setName(employee.getName());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    return ResponseEntity.ok(e);})
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(e -> {
                    employees.remove(e);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }
}
