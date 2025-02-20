package com.projection.Optimised.Controller;

import com.projection.Optimised.Model.UserEntity;
import com.projection.Optimised.Projections.BasicUserInfo;
import com.projection.Optimised.Repository.UserRepository;
import com.projection.Optimised.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/adduser")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity user) {
        try {
            userService.adduser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<Optional<BasicUserInfo>> getUserById(@PathVariable UUID id) {
        try {
           Optional<BasicUserInfo> usr= userService.fetchUserById(id);
            return new ResponseEntity<>(usr, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
