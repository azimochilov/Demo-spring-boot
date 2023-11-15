package com.example.demo.model;

public class Student {
    private Long Id;
    private String Name;
    private String Course;

    public Student(Long id, String name, String course) {
        Id = id;
        Name = name;
        Course = course;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }
}
