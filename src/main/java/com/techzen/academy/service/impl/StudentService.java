package com.techzen.academy.service.impl;

import com.techzen.academy.model.Student;
import com.techzen.academy.repository.IStudentRepository;
import com.techzen.academy.service.IStudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService implements IStudentService {
    //@Autowired //DI => tiêm sự phụ thuộc
    IStudentRepository studentRepository;

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student findById(int id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
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
