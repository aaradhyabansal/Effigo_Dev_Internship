package com.fullStackTodo.TodoApp.Controllers;

import com.fullStackTodo.TodoApp.Models.Todo;
import com.fullStackTodo.TodoApp.Services.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username)
    {
        return todoService.findByUsername(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username,@PathVariable int id)
    {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable int id)
    {
        System.out.println("delete called");
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodoById(@PathVariable String username, @PathVariable int id,@RequestBody Todo todo)
    {

        todoService.updateTodo(todo);
        return todo;
    }
    @PostMapping("/users/{username}/todos")
    public Todo createTodoById(@PathVariable String username,@RequestBody Todo todo)
    {

      Todo createdTodo=  todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
        return createdTodo
                ;
    }
}
