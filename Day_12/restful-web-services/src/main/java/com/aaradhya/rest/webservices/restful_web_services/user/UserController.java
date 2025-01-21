package com.aaradhya.rest.webservices.restful_web_services.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userDaoService.findAll();
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userDaoService.findById(id);
    }
    @PostMapping("/users")
    public void addUser(@Valid @RequestBody User user) {
         userDaoService.save(user);
    }
    @DeleteMapping ("/users/{id}")
    public void deleteUserById(@PathVariable int id) {
         userDaoService.deleteById(id);
    }
}
