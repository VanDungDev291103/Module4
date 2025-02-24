package com.techzen.academy.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
     Integer id;
     String name;
     Gender gender;
     double salary;
     String phone;
     Integer departmentId;

//    public Employee() {
//    }
//
//    public Employee(int id, String name, Gender gender, double salary, String phone) {
//        this.id = id;
//        this.name = name;
//        this.gender = gender;
//        this.salary = salary;
//        this.phone = phone;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Gender getGender() {
//        return gender;
//    }
//
//    public void setGender(Gender gender) {
//        this.gender = gender;
//    }
//
//    public double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public enum Gender {
        MALE, FEMALE,OTHER
    }
}
