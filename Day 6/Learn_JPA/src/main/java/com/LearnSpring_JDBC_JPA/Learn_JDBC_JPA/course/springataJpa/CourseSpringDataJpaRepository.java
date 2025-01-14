package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.springataJpa;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseSpringDataJpaRepository extends JpaRepository<Course,Long> {
    List<Course> findByAuthor(String Author);
    List<Course> findByName(String Name);

}
