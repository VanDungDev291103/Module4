package com.techzen.academy.dto.employee;


import com.techzen.academy.model.Employee;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSearchRequest {
    Integer id;
    String name;
    Employee.Gender gender;
    double salary;
    String phone;
    Integer departmentId;
}
