package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.jdbc;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseJdbcCommandLineRunner implements CommandLineRunner {

    @Autowired
    CourseJdbcRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(2,"Spring Boot","Springeee"));
        repository.insert(new Course(3,"Spring JDBC","Springeee"));
        repository.insert(new Course(4,"Spring JPA","Springeee"));
        repository.delete(new Course(3,"Spring Boot","Springeee"));
        System.out.println(repository.findById(4));
//        System.out.println(repository.findById(3));
        List<Course> courses=repository.findAll();
        courses.forEach(System.out::println);



    }
}
