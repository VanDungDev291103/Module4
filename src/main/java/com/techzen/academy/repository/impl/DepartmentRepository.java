package com.techzen.academy.repository.impl;

import com.techzen.academy.model.Department;
import com.techzen.academy.repository.IDepartmentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentRepository implements IDepartmentRepository {

    private List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "kế toán"),
                    new Department(2, "marketing"),
                    new Department(3, "Quản lý"))
    );

    @Override
    public List<Department> findAll() {
        return departments.stream().toList();
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departments.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Department save(Department department) {
        return findById(department.getId()).map(d -> {
            d.setName(department.getName());
            return d;
        }).orElseGet(() -> {
            department.setId((int) (Math.random() * 10000));
            departments.add(department);
            return department;
        });
    }

    @Override
    public void delete(Integer id) {
        findById(id).ifPresent(d -> departments.remove(d));
    }
}
