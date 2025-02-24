package com.techzen.academy.repository;

import com.techzen.academy.model.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentRepository {
    List<Department> findAll();
    Optional<Department> findById(Integer id);
    Department save(Department department);
    void delete(Integer id);
}
