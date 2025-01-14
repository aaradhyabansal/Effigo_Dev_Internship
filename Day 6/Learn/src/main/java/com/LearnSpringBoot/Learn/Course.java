package com.LearnSpringBoot.Learn;

public class Course {
    private int id;
    private String CourseName;
    private String Author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Course(int id, String courseName, String author) {
        this.id = id;
        CourseName = courseName;
        Author = author;
    }


}
