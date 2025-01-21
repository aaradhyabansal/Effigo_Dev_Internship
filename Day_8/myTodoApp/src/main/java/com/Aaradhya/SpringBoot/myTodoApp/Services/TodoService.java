package com.Aaradhya.SpringBoot.myTodoApp.Services;

import com.Aaradhya.SpringBoot.myTodoApp.Repository.TodoRepository;
import com.Aaradhya.SpringBoot.myTodoApp.Models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodosByUsername(String username)
    {
        List<Todo> todoo=todoRepository.findByUsername(username);
        return todoo;

    }

    public void addTodo(String username,String description,LocalDate targetDate,boolean done)
    {
        Todo todo=new Todo(username,description,targetDate,done);
        todoRepository.save(todo);
    }
    public void deleteTodo(long id)
    {
        todoRepository.deleteById(id);
    }
    public Todo findById(long id)
    {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));


    }
    public void updateTodo(long id, String description, LocalDate targetDate) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todo.setTargetDate(targetDate);
        todo.setDescription(description);
        todoRepository.save(todo);
    }

}
