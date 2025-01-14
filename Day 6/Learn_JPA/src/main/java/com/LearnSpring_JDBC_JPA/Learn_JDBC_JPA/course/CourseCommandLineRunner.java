package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.jpa.CourseJPARepository;
import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.springataJpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    CourseJPARepository repository;

    @Autowired
    CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(2,"SpringggggggBoot","Springeeee"));
        repository.save(new Course(3,"SpringJDBC","Springeee"));
        repository.save(new Course(4,"SpringJPA","Springeee"));
//        repository.deleteById(3l);
        System.out.println(repository.findById(4l));
//        System.out.println(repository.findById(3));
        List<Course> courses=repository.findAll();
        courses.forEach(System.out::println);
        System.out.println(repository.findByAuthor("Springee"));
        System.out.println(repository.findByAuthor("Sprin"));
        System.out.println(repository.findByName("SpringJDBC"));
        System.out.println(repository.findByName("Sprin"));





    }
}
