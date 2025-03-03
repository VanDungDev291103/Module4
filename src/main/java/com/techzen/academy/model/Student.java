package com.techzen.academy.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

//nếu không muốn tạo constructor và getter setter hoặc là k muốn private thì sử dụng lombok
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student {
    //private
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    //private
    String name;
    //private
    double score;

    @ManyToOne
    clazz clazz;

//    public Student() {
//    }
//
//    public Student(int id, String name, double score) {
//        this.id = id;
//        this.name = name;
//        this.score = score;
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
//    public double getScore() {
//        return score;
//    }
//
//    public void setScore(double score) {
//        this.score = score;
//    }
}
