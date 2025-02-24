package com.techzen.academy.repository.impl;

import com.techzen.academy.dto.employee.EmployeeSearchRequest;
import com.techzen.academy.model.Employee;
import com.techzen.academy.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {
    private List<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee(1, "dũng", Employee.Gender.MALE, 180000, "0387161032", 1),
                    new Employee(2, "LY", Employee.Gender.FEMALE, 200000, "0987777777", 2),
                    new Employee(3, "Hai", Employee.Gender.MALE, 30000, "03873333333", 3),
                    new Employee(4, "Ân", Employee.Gender.FEMALE, 180000, "0387161111", 2),
                    new Employee(5, "Dorran", Employee.Gender.MALE, 5000000, "0387555555", 3)
            )
    );

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        return employees.stream()
                .filter(e -> employeeSearchRequest.getName() == null
                        || e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase()))
                .filter(e -> employeeSearchRequest.getGender() == null
                        || e.getGender() == employeeSearchRequest.getGender())
                .filter(e -> employeeSearchRequest.getSalary() == 0
                        || e.getSalary() >= employeeSearchRequest.getSalary())
                .filter(e -> employeeSearchRequest.getPhone() == null
                        || e.getPhone().contains(employeeSearchRequest.getPhone()))
                .filter(e -> employeeSearchRequest.getDepartmentId() == null
                        || e.getDepartmentId().equals(employeeSearchRequest.getDepartmentId()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employees.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Employee save(Employee employee) {
        return findById(employee.getId()).map(e -> {
                    e.setName(employee.getName());
                    e.setGender(employee.getGender());
                    e.setSalary(employee.getSalary());
                    e.setPhone(employee.getPhone());
                    e.setDepartmentId(employee.getDepartmentId());
                    return e;
                })
                .orElseGet(() -> {
                    employee.setId((int) (Math.random() * 1000));
                    employees.add(employee);
                    return employee;
                });
    }

    @Override
    public void delete(Integer id) {
        //employees.removeIf(employee -> employee.getId().equals(id));
        findById(id).ifPresent(e -> employees.remove(e));
    }
}
