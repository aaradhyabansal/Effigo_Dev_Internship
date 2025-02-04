package com.mapper.practice.Service;

import com.mapper.practice.DTO.ExternalDto;
import com.mapper.practice.DTO.InternalDto;
import com.mapper.practice.Mapper.UserMapper;
import com.mapper.practice.Model.UserEntity;
import com.mapper.practice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<InternalDto> getAllUsers()
    {
       return userRepository.findAll().stream()
               .map(userMapper::EntityToDto)
               .collect(Collectors.toList());
    }
    public Optional<InternalDto> getUserById(long id)
    {
        return userRepository.findById(id)
                .map(userMapper::EntityToDto);
    }
    public InternalDto createUser(ExternalDto externalDto)
    {
        InternalDto internalDto =userMapper.DtoToDto(externalDto);
        UserEntity userEntity = userMapper.DtoToEntity(internalDto);
        userRepository.save(userEntity);
        return internalDto;
    }
    public InternalDto updateUser(long id, ExternalDto externalDto)
    {
        UserEntity userFound=userRepository.findById(id) .orElseThrow(() -> new RuntimeException("User not found"));
        InternalDto internalDto =userMapper.DtoToDto(externalDto);
        userFound.setName(internalDto.getName());
        userFound.setEmail(internalDto.getEmail());
        userRepository.save(userFound);
        return internalDto;
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
