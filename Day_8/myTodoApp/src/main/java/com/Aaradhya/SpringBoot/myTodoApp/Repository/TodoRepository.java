package com.Aaradhya.SpringBoot.myTodoApp.Repository;

import com.Aaradhya.SpringBoot.myTodoApp.Models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
List<Todo> findByUsername(String username);
}
