package com.techzen.academy.controller;

import com.techzen.academy.dto.ApiResponse;
import com.techzen.academy.dto.employee.EmployeeSearchRequest;
import com.techzen.academy.exception.AppException;
import com.techzen.academy.exception.ErrorCode;
import com.techzen.academy.model.Employee;
import com.techzen.academy.service.impl.EmployeeService;
import com.techzen.academy.util.JsonResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/employees")
public class EmployeeManagementController {

    EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<?> getAll(EmployeeSearchRequest employeeSearchRequest) {
        return JsonResponse.ok(employeeService.findByAttributes(employeeSearchRequest));
    }

    // cách ngắn gọn
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
        return employeeService.findById(id).map(ResponseEntity::ok)
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
    public ResponseEntity<ApiResponse<Employee>> create(@RequestBody Employee employee) {
        return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        employeeService.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        employeeService.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employeeService.delete(id);
        return JsonResponse.noContent();
    }
}
