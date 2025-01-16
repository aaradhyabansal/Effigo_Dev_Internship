package com.Aaradhya.SpringBoot.myTodoApp.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("username")
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService ;


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String goToWelcome(ModelMap model) {
        model.put("username",getLoggedInUsernamw());
        return "welcome";
    }

    private String getLoggedInUsernamw()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
