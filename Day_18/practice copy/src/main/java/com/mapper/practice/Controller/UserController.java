package com.mapper.practice.Controller;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<InternalDto> getUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public Optional<InternalDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @PostMapping("/adduser")
    public InternalDto addUser(@RequestBody ExternalDto externalDto) {
        return userService.createUser(externalDto);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @PutMapping("/user/{id}")
    public InternalDto updateUser(@PathVariable Long id, @RequestBody ExternalDto externalDto) {
        return userService.updateUser(id, externalDto);
    }
}
