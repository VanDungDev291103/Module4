package com.techzen.academy.service;

import com.techzen.academy.dto.employee.EmployeeSearchRequest;
import com.techzen.academy.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);
    Optional<Employee> findById(Integer id);
    Employee save(Employee employee);
    void delete(Integer id);
}
