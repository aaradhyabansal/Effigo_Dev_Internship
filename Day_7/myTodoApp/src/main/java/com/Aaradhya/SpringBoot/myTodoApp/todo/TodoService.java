package com.Aaradhya.SpringBoot.myTodoApp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static long idCnt=0;
    private static List<Todo> todos=new ArrayList<>();

    static{
        todos.add(new Todo(++idCnt, "Aaradhya", "Complete Spring Boot Course", LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Prepare for project demo", LocalDate.now().plusWeeks(2), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Submit weekly report", LocalDate.now().plusDays(3), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Review team performance", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Finalize presentation slides", LocalDate.now().plusWeeks(1), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Plan quarterly goals", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Update resume for job applications", LocalDate.now().plusMonths(4), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Organize team outing", LocalDate.now().plusWeeks(5), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Start a new side project", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(++idCnt, "Aaradhya", "Clean up project backlog", LocalDate.now().plusDays(7), false));
     }
     public List<Todo> getTodosByUsername(String username)
     {
         return todos;
     }

     public void addTodo(String username,String description,LocalDate targetDate,boolean done)
     {
        Todo todo=new Todo(++idCnt,username,description,targetDate,done);
        todos.add(todo);
     }
}
