package com.sqq.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer classId;

    private String studentName;

    private List<String> money;

    public User() {

    }

    public User(Integer classId, String studentName) {
        this.classId = classId;
        this.studentName = studentName;
    }

    public User(Integer classId, String studentName, List<String> money) {
        this.classId = classId;
        this.studentName = studentName;
        this.money = money;
    }
}
