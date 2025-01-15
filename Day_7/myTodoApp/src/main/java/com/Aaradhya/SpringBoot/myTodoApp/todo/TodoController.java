package com.Aaradhya.SpringBoot.myTodoApp.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
    public String showNewTodoPage()
    {
        return "addtodo";
    }
    @RequestMapping(value="add-todo",method= RequestMethod.POST)
    public String addNewTodoPage()
    {
//        model.addAttribute("todos",todos);
        return "redirect:list-todos";
    }

}
