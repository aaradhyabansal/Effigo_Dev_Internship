package com.projection.Optimised.Service;

import com.projection.Optimised.Model.UserEntity;
import com.projection.Optimised.Projections.BasicUserInfo;
import com.projection.Optimised.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity adduser(UserEntity user) {
        return userRepository.save(user);
    }
    public Optional<BasicUserInfo> fetchUserById(UUID id) {
        return userRepository.findByUserId(id);
    }
}
