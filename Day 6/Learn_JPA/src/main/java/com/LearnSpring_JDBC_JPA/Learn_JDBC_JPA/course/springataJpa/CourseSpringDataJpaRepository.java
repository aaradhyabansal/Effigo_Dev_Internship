package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.springataJpa;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSpringDataJpaRepository extends JpaRepository<Course,Long> {
}
