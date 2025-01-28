package com.RoleBasedAccess.Ed.Assign.config;

import com.RoleBasedAccess.Ed.Assign.Models.Roles;
import com.RoleBasedAccess.Ed.Assign.Models.Users;
import com.RoleBasedAccess.Ed.Assign.Service.UserService;
import com.RoleBasedAccess.Ed.Assign.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserService userService;

    public DataLoader(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Roles adminRole = new Roles();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        Roles userRole = new Roles();
        userRole.setName("TEACHER");
        roleRepository.save(userRole);

        Users adminUser = new Users();
        adminUser.setUsername("admin");
        adminUser.setPassword("admin123");
        adminUser.setEmail("admin@example.com");
        adminUser.setRole(adminRole);
        userService.addNewUser(adminUser);

        Users normalUser = new Users();
        normalUser.setUsername("TEACHER");
        normalUser.setPassword("teacher123");
        normalUser.setEmail("teacher@example.com");
        normalUser.setRole(userRole);
        userService.addNewUser(normalUser);
    }
}
