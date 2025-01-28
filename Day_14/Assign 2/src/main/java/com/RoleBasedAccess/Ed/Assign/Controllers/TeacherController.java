package com.RoleBasedAccess.Ed.Assign.Controllers;

import com.RoleBasedAccess.Ed.Assign.Models.Users;
import com.RoleBasedAccess.Ed.Assign.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private UserService userService;

    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String TeacherDashboard()
    {
        return "Welcome to Teacher Dashboard";
    }
    @GetMapping("/users")
    public List<Users> getAllUsers() {
        System.out.println(userService.getAllUsers());
        return userService.getAllUsers();
    }
    @PostMapping("/adduser")
    public void AddUser(@RequestBody Users users) {
        userService.addNewUser(users);
    }
    @DeleteMapping("deleteuser/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUserById(id);
    }
    @PostMapping("/update/{id}")
    public void updateUser(@PathVariable long id, Users users) {
        userService.UpdateUser(users,id);
    }
}
