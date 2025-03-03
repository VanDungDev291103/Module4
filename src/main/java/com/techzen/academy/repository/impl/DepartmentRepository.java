package com.techzen.academy.repository.impl;

import com.techzen.academy.model.Department;
import com.techzen.academy.model.Student;
import com.techzen.academy.repository.IDepartmentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        List<Department> departmentList = new ArrayList<>();

        try (
                PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select id, name from department");
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                departmentList.add(new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departmentList;
    }

    @Override
    public Optional<Department> findById(Integer id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("select id,name from department where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Department department = Department.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                return Optional.of(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Department save(Department department) {
        try {
            if (findById(department.getId()) == null) {//create
                PreparedStatement preparedStatement = BaseRepository.getConnection()
                        .prepareStatement("insert into departmant (name) VALUE (?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, department.getName());

                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    department.setId(rs.getInt(1));
                }
            } else { // update
                PreparedStatement preparedStatement = BaseRepository.getConnection()
                        .prepareStatement("update student set name = ? where id = ?;");
                preparedStatement.setString(1, department.getName());
                preparedStatement.setInt(2, department.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }

    @Override
    public void delete(Integer id) {
        try (
                PreparedStatement deleteDepartment = BaseRepository.getConnection().prepareStatement("delete from department where id = ?")) {

            deleteDepartment.setInt(1, id);

            int rowsAffected = deleteDepartment.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Không tìm thấy phòng ban với ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
