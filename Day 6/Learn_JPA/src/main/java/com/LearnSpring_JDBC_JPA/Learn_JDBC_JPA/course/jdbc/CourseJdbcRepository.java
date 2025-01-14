package com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.jdbc;

import com.LearnSpring_JDBC_JPA.Learn_JDBC_JPA.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseJdbcRepository {
    @Autowired
    private JdbcTemplate springJdbc;

    public static String INSERT_QUERY=
            """
                    insert into course (id,Name,Author)
                    values (?,?,?);
                    """;
    public static String DELETE_QUERY=
            """
                    DELETE FROM course where id=?;
                    """;

    public static String GET_QUERY=
            """
                    SELECT * FROM course where id=?;
                    """;
    public static String GET_ALL=
            """
                    SELECT * FROM course;
                    """;


    public void insert(Course course)
    {
        springJdbc.update(INSERT_QUERY,course.getId(),course.getName(),course.getAuthor());
    }

    public void delete(Course course)
    {
        springJdbc.update(DELETE_QUERY,course.getId());
    }
    public Course findById(long id)
    {
        return springJdbc.queryForObject(GET_QUERY,new BeanPropertyRowMapper<>(Course.class),id);
    }
    public List<Course> findAll()
    {
        return springJdbc.query(GET_ALL,new BeanPropertyRowMapper<>(Course.class));
    }

}
