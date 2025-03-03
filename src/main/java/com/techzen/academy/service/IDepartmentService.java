package com.techzen.academy.service;

import com.techzen.academy.model.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    List<Department> findAll();

    Optional<Department> findById(Integer id);

    Department save(Department department);

    void delete(Integer id);
}
