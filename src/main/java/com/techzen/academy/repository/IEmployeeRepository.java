package com.techzen.academy.repository;

import com.techzen.academy.dto.employee.EmployeeSearchRequest;
import com.techzen.academy.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository {
    List<Employee>  findByAttributes(EmployeeSearchRequest employeeSearchRequest);
    Optional<Employee> findById(Integer id); // sử dụng Optional để tránh NullPointerException

    Employee save(Employee employee);

    void delete(Integer id);
}
