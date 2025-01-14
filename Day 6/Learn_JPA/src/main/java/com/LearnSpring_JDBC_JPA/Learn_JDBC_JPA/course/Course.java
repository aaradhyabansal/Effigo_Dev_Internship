package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
public class Course {
    @Id
    private int id;
    @Column(name="Name")
    private String Name;
    @Column(name="Author")
    private String Author;

    public Course()
    {

    }

    public Course(int id, String name, String author) {
        this.id = id;
        Name = name;
        Author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }

}
