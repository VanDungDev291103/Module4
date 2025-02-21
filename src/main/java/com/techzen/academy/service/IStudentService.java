package com.techzen.academy.service;

import com.techzen.academy.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findByName(String name);
    Student findById(int id);
    Student save(Student student);
}
