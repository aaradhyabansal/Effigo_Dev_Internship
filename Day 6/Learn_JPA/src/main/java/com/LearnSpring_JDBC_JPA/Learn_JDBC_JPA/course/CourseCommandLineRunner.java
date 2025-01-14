package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.jpa.CourseJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

    @Autowired
    CourseJPARepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(2,"Springgggggg Boot","Springeee"));
        repository.insert(new Course(3,"Spring JDBC","Springeee"));
        repository.insert(new Course(4,"Spring JPA","Springeee"));
        repository.delete(3);
        System.out.println(repository.getCourse(4));
//        System.out.println(repository.findById(3));
        List<Course> courses=repository.getAllCourses();
        courses.forEach(System.out::println);



    }
}
