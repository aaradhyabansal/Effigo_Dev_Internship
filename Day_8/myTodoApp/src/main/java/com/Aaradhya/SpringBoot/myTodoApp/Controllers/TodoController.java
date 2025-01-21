package com.Aaradhya.SpringBoot.myTodoApp.Controllers;

import com.Aaradhya.SpringBoot.myTodoApp.Models.Todo;
import com.Aaradhya.SpringBoot.myTodoApp.Services.TodoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        List<Todo> todos=todoService.getTodosByUsername(getLoggedInUsername(model));
        model.addAttribute("todos",todos);
        return "listtodos";
    }


    @RequestMapping(value="add-todo",method= RequestMethod.GET)
    public String showNewTodoPage(ModelMap model)
    {
        Todo todo=new Todo(getLoggedInUsername(model),"",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "addtodo";
    }
    @RequestMapping(value="add-todo",method= RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result)
    {       if(result.hasErrors()){
        return "addtodo";
    }
        todoService.addTodo(getLoggedInUsername(model),todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";
    }
    @RequestMapping(value="delete-todo",method= RequestMethod.GET)
    public String deleteTodoPage(@RequestParam long id, ModelMap model)
    {
        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value="update-todo",method= RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model)
    {

    Todo todo=todoService.findById(id);
        model.addAttribute("todo",todo);
        return "addtodo";
    }
    @RequestMapping(value="update-todo",method= RequestMethod.POST)
    public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result)
    {
        if(result.hasErrors()){
        return "addtodo";
    }
//        model.addAttribute("todos",todos);
       todoService.updateTodo(todo.getId(),todo.getDescription(),todo.getTargetDate());
        return "redirect:list-todos";
    }

    private static String getLoggedInUsername(ModelMap model) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
