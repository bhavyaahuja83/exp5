package com.mycompany.exp5;

public class Student {
    private final int id;
    private final String name;
    private final String course;
    private final String email;

    public Student(int id, String name, String course, String email) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }
}