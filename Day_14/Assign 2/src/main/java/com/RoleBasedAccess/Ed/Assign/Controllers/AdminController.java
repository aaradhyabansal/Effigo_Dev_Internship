package com.RoleBasedAccess.Ed.Assign.Controllers;

import com.RoleBasedAccess.Ed.Assign.Models.Users;
import com.RoleBasedAccess.Ed.Assign.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {

       return userService.getAllUsers();
    }
    @PostMapping("/addUser")
    public void AddUser(Users users) {
        userService.AddNewUser(users);
    }
    @DeleteMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }
    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable long id, Users users) {
        userService.UpdateUser(users,id);
    }


}
