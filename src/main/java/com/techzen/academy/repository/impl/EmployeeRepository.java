package com.techzen.academy.repository.impl;

import com.techzen.academy.dto.employee.EmployeeSearchRequest;
import com.techzen.academy.model.Employee;
import com.techzen.academy.repository.IEmployeeRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeRepository implements IEmployeeRepository {
//    private List<Employee> employees = new ArrayList<>(
//            Arrays.asList(
//                    new Employee(1, "dũng", Employee.Gender.MALE, 180000, "0387161032", 1),
//                    new Employee(2, "LY", Employee.Gender.FEMALE, 200000, "0987777777", 2),
//                    new Employee(3, "Hai", Employee.Gender.MALE, 30000, "03873333333", 3),
//                    new Employee(4, "Ân", Employee.Gender.FEMALE, 180000, "0387161111", 2),
//                    new Employee(5, "Dorran", Employee.Gender.MALE, 5000000, "0387555555", 3)
//            )
//    );

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest) {
        List<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("SELECT * FROM employee WHERE name LIKE ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        return employees.stream()
//                .filter(e -> employeeSearchRequest.getName() == null
//                        || e.getName().toLowerCase().contains(employeeSearchRequest.getName().toLowerCase()))
//                .filter(e -> employeeSearchRequest.getGender() == null
//                        || e.getGender() == employeeSearchRequest.getGender())
//                .filter(e -> employeeSearchRequest.getSalary() == 0
//                        || e.getSalary() >= employeeSearchRequest.getSalary())
//                .filter(e -> employeeSearchRequest.getPhone() == null
//                        || e.getPhone().contains(employeeSearchRequest.getPhone()))
//                .filter(e -> employeeSearchRequest.getDepartmentId() == null
//                        || e.getDepartmentId().equals(employeeSearchRequest.getDepartmentId()))
//                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("select  id,name,gender,salary,phone,departmentId from employee where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setGender(Employee.Gender.valueOf(resultSet.getString("gender")));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setDepartmentId(resultSet.getInt("department_id"));

                return Optional.of(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        try {
            if (findById(employee.getId()) == null) {//create
                PreparedStatement preparedStatement = BaseRepository.getConnection()
                        .prepareStatement("insert into departmant (name,gender,salary,phone,departmentId) VALUE (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, String.valueOf(employee.getGender()));
                preparedStatement.setDouble(3, employee.getSalary());
                preparedStatement.setString(4, employee.getPhone());
                preparedStatement.setInt(5, employee.getDepartmentId());

                preparedStatement.executeUpdate();

                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
            } else { // update
                PreparedStatement preparedStatement = BaseRepository.getConnection()
                        .prepareStatement("update student set name = ?, gender =? ,salary = ?,phone = ?,departmentId = ? where id = ?;");
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setString(2, String.valueOf(employee.getGender()));
                preparedStatement.setDouble(3, employee.getSalary());
                preparedStatement.setString(4, employee.getPhone());
                preparedStatement.setInt(5, employee.getDepartmentId());
                preparedStatement.setInt(6, employee.getId());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement deleteEmployee = BaseRepository.getConnection().prepareStatement("delete from employee where id = ?")) {

            deleteEmployee.setInt(1, id);

            int rowsAffected = deleteEmployee.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("Không tìm thấy nhân viên với ID: " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //employees.removeIf(employee -> employee.getId().equals(id));
        //findById(id).ifPresent(e -> employees.remove(e));
    }
}
