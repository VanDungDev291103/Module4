package com.techzen.academy.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

//nếu không muốn tạo constructor và getter setter hoặc là k muốn private thì sử dụng lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    //private
    int id;
    //private
    String name;
    //private
    double score;

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
