package com.LearnSpringBoot.Learn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses()
    {
        return Arrays.asList(
                new Course(1,"Learn Java17","By Hello World"),
                new Course(2,"Learn git","By Hello Git"),
                new Course(3,"Learn Maven","By Hello Maven"),
                new Course(4,"Learn Spring","By Hello Spring")
        );
    }

}
