package com.techzen.academy.repository;

import com.techzen.academy.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findByName(String name);

    Student findById(int id);

    Student save(Student student);
}
