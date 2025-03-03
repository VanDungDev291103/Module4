package com.techzen.academy.repository.impl;

import com.techzen.academy.model.Student;
import com.techzen.academy.repository.IStudentRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class StudentRepository implements IStudentRepository {

    public List<Student> findByName(String name) {
//        List<Student> studentSearch = new ArrayList<>();
//
//        try {
//            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("select id,name,score from student where name like concat('%',?,'%')");
//            preparedStatement.setString(1, name);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                studentSearch.add(Student.builder()
//                        .id(resultSet.getInt("id"))
//                        .name(resultSet.getString("name"))
//                        .score(resultSet.getDouble("score"))
//                        .build());
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return studentSearch;
        Session session = ConnectionUtil.sessionFactory.openSession(); // mở phiên làm việc
        List<Student> students = null;
        try {                                   //Sử dụng HQL ể lấy danh sách sinh viên
            students = session.createQuery("FROM Student WHERE name LIKE CONCAT('%',:name, '%')")
                    .setParameter("name", name)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // đóng phiên làm vc sau khi lấy xong danh sách
        }
        return students;
    }

    public Student findById(int id) {
//        try {
//            PreparedStatement preparedStatement = BaseRepository.getConnection()
//                    .prepareStatement("select id,name,score from student where id = ?");
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                return Student.builder()
//                        .id(resultSet.getInt("id"))
//                        .name(resultSet.getString("name"))
//                        .score(resultSet.getDouble("score"))
//                        .build();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
        Session session = ConnectionUtil.sessionFactory.openSession(); // mở phiên làm việc
        Student student = null;
        try {                                   //Sử dụng HQL ể lấy danh sách sinh viên
            student = (Student) session.createQuery("FROM Student WHERE id = :id")
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // đóng phiên làm vc sau khi lấy xong danh sách
        }
        return student;
    }

    public Student save(Student student) {
//        try {
//            if (findById(student.getId()) == null) {//create
//                PreparedStatement preparedStatement = BaseRepository.getConnection()
//                        .prepareStatement("insert into student (name, score) VALUE (?,?);", Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, student.getName());
//                preparedStatement.setDouble(2, student.getScore());
//
//                preparedStatement.executeUpdate();
//
//                ResultSet rs = preparedStatement.getGeneratedKeys();
//                if (rs.next()) {
//                    student.setId(rs.getInt(1));
//                }
//            } else { // update
//                PreparedStatement preparedStatement = BaseRepository.getConnection()
//                        .prepareStatement("update student set name = ? , score = ? where id = ?;");
//                preparedStatement.setString(1, student.getName());
//                preparedStatement.setDouble(2, student.getScore());
//                preparedStatement.setInt(3, student.getId());
//
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
////        for (Student std : this.students) {
////            if (std.getId() == student.getId()) {
////                //std.setId(id);
////                std.setName(student.getName());
////                std.setScore(student.getScore());
////                return std;
////            }
////        }
//
////        student.setId((int) (Math.random() * 10));
////        students.add(student);
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.saveOrUpdate(student);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback(); // rollback nếu có lỗi
                }
                throw new RuntimeException(e);
            }
        }
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
