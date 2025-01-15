package com.Aaradhya.SpringBoot.myTodoApp.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos=new ArrayList<>();

    static{
        todos.add(new Todo(1, "Aaradhya", "Complete Spring Boot Course", LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(2, "Aaradhya", "Prepare for project demo", LocalDate.now().plusWeeks(2), false));
        todos.add(new Todo(3, "Aaradhya", "Submit weekly report", LocalDate.now().plusDays(3), false));
        todos.add(new Todo(4, "Aaradhya", "Review team performance", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(5, "Aaradhya", "Finalize presentation slides", LocalDate.now().plusWeeks(1), false));
        todos.add(new Todo(6, "Aaradhya", "Plan quarterly goals", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(7, "Aaradhya", "Update resume for job applications", LocalDate.now().plusMonths(4), false));
        todos.add(new Todo(8, "Aaradhya", "Organize team outing", LocalDate.now().plusWeeks(5), false));
        todos.add(new Todo(9, "Aaradhya", "Start a new side project", LocalDate.now().plusMonths(6), false));
        todos.add(new Todo(10, "Aaradhya", "Clean up project backlog", LocalDate.now().plusDays(7), false));
     }
     public List<Todo> getTodosByUsername(String username)
     {
         return todos;
     }
}
