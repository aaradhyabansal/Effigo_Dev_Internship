package com.Aaradhya.SpringBoot.myTodoApp.login;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService  {

    public boolean authenticate(String username, String password) {
        boolean validUsername=username.equalsIgnoreCase("aaradhya");
        boolean validPassword=password.equalsIgnoreCase("abcd1234");

        return validUsername && validPassword;


    }
}
