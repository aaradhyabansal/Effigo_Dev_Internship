package com.Aaradhya.SpringBoot.myTodoApp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

//@Service
//public class TodoService {
//    private static long idCnt=0;
//    private static List<Todo> todos=new ArrayList<>();
//
//    static{
//        todos.add(new Todo(++idCnt, "Aaradhya", "Complete Spring Boot Course", LocalDate.now().plusMonths(1), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Prepare for project demo", LocalDate.now().plusWeeks(2), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Submit weekly report", LocalDate.now().plusDays(3), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Review team performance", LocalDate.now().plusMonths(2), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Finalize presentation slides", LocalDate.now().plusWeeks(1), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Plan quarterly goals", LocalDate.now().plusMonths(3), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Update resume for job applications", LocalDate.now().plusMonths(4), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Organize team outing", LocalDate.now().plusWeeks(5), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Start a new side project", LocalDate.now().plusMonths(6), false));
//        todos.add(new Todo(++idCnt, "Aaradhya", "Clean up project backlog", LocalDate.now().plusDays(7), false));
//     }
//     public List<Todo> getTodosByUsername(String username)
//     {
//         List<Todo> todoo=todos.stream().filter(todo->todo.getUsername().equalsIgnoreCase(username)).toList();
//         return todoo;
//
//     }
//
//     public void addTodo(String username,String description,LocalDate targetDate,boolean done)
//     {
//        Todo todo=new Todo(++idCnt,username,description,targetDate,done);
//        todos.add(todo);
//     }
//    public void deleteTodo(long id)
//    {
//       todos.removeIf(todo->todo.getId()==id);
//    }
//    public Todo findById(long id)
//    {
//        Todo todoo=todos.stream().filter(todo->todo.getId()==id).findFirst().get();
//
//        return todoo;
//    }
//    public void updateTodo(long id,String description,LocalDate targetDate)
//    {
//        Todo todoo=todos.stream().filter(todo->todo.getId()==id).findFirst().get();
//
//        todoo.setDescription(description);
//        todoo.setTargetDate(targetDate);
//
//    }
//}
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

