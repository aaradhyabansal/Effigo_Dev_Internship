package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.jpa;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseJPARepository {
    @PersistenceContext
    EntityManager entityManager;

    public void insert(Course course)
    {
        entityManager.merge(course);
    }

    public Course getCourse(long id)
    {
        return entityManager.find(Course.class,id);
    }

    public void delete(long id)
    {
        Course course=entityManager.find(Course.class,id);
        entityManager.remove(course);
    }
    public List<Course> getAllCourses()
    {
        String jpql = "SELECT c FROM Course c";
        return entityManager.createQuery(jpql,Course.class).getResultList();
    }
}
