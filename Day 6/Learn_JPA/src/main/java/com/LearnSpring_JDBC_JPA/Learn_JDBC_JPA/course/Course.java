package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
public class Course {
    @Id
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="author")
    private String author;

    public Course()
    {

    }

    public Course(int id, String name, String author) {
        this.id = id;
        name = name;
        author = author;
    }

    public int getId() {
        return id;
    }

    public String getname() {
        return name;
    }

    public String getauthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setname(String name) {
        name = name;
    }

    public void setauthor(String author) {
        author = author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

}
