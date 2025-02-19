package com.Aaradhya.SpringBoot.myTodoApp.todo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("username")

public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model)
    {
        List<Todo> todos=todoService.getTodosByUsername("Aaradhya");
        model.addAttribute("todos",todos);
        System.out.println(todos);
        return "listtodos";
    }

    @RequestMapping(value="add-todo",method= RequestMethod.GET)
    public String showNewTodoPage(ModelMap model)
    {
        Todo todo=new Todo(0,(String) model.get("username"),"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "addtodo";
    }
    @RequestMapping(value="add-todo",method= RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result)
    {       if(result.hasErrors()){
        return "addtodo";
    }
//        model.addAttribute("todos",todos);
        todoService.addTodo((String) model.get("username"),todo.getDescription(), LocalDate.now().plusYears(1),false);
        return "redirect:list-todos";
    }

}
