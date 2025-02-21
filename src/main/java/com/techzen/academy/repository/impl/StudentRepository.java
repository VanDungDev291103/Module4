package com.techzen.academy.repository.impl;

import com.techzen.academy.model.Student;
import com.techzen.academy.repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Dũng", 4.5),
                    new Student(2, "Doran", 8),
                    new Student(3, "hello", 3)
            )
    );

    public List<Student> findByName(String name) {
        List<Student> studentSearch = new ArrayList<>();

        for (Student students : students) {
            if (students.getName().contains(name)) { // like
                studentSearch.add(students);
            }
        }
        return studentSearch;
    }

    public Student findById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student save(Student student) {
        for (Student std : this.students) {
            if (std.getId() == student.getId()) {
                //std.setId(id);
                std.setName(student.getName());
                std.setScore(student.getScore());
                return  std;
            }
        }

        student.setId((int) (Math.random()*10));
        students.add(student);
        return student;
    }

//    public Student update(Student student) {
//        for (Student std : this.students) {
//            if (std.getId() == student.getId()) {
//                //std.setId(id);
//                std.setName(student.getName());
//                std.setScore(student.getScore());
//                return  std;
//            }
//        }
//        throw new ApiException(ErrorCode.STUDENT_NOT_EXIST);
//    }
}
