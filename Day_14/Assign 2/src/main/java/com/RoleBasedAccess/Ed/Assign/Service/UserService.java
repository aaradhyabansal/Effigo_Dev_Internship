package com.RoleBasedAccess.Ed.Assign.Service;

import com.RoleBasedAccess.Ed.Assign.Models.Roles;
import com.RoleBasedAccess.Ed.Assign.Models.Users;
import com.RoleBasedAccess.Ed.Assign.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void AddNewUser(Users users) {
        userRepository.save(users);
    }
    public List<Users> getAllUsers() {
       return userRepository.findAll();
    }
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
    public void UpdateUser(Users users, long id) {
        Users usersT =userRepository.findById(id).orElse(null);
        if(usersT !=null) {
            usersT.setEmail(users.getEmail());
            usersT.setPassword(users.getPassword());
            usersT.setRole(users.getRole());
            usersT.setUsername(users.getUsername());
            userRepository.save(usersT);
        }

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =userRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Roles roles = users.getRole();
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + roles.getName())
        );

        return new org.springframework.security.core.userdetails.User(
                users.getUsername(),
                users.getPassword(),
                authorities);


    }



}
