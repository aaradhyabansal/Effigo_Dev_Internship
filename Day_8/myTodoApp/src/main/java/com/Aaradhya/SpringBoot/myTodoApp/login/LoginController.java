package com.Aaradhya.SpringBoot.myTodoApp.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("username")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService ;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String goToLogin() {
        return "login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String goToWelcome(@RequestParam String username, @RequestParam String password, ModelMap model) {

        model.addAttribute("username", username);
        model.addAttribute("password", password);
        if(authenticationService.authenticate(username,password))
        return "welcome";

        return "login";
    }
}
