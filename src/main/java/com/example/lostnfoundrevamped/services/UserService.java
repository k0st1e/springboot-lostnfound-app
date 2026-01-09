package com.example.lostnfoundrevamped.services;

import com.example.lostnfoundrevamped.entities.UserEntity;
import com.example.lostnfoundrevamped.entities.dto.RegisterDto;
import com.example.lostnfoundrevamped.entities.dto.UserDto;
import com.example.lostnfoundrevamped.entities.enums.UserRole;
import com.example.lostnfoundrevamped.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    // Here we create a `UserDto` with `username` and `password`.
    public UserDto dologin(String username, String password) {

        UserEntity user = userRepository.findByUsername(username);

        // if (user != null && password.equals(user.getPassword())) {
        // The above code was there as I was working with my Database and my passwords were not hashed in the beginning.
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return mapToDto(user);
        } else
            return null;
    }

    // This is paired with the login. We return the username, name and role when the passwords match.
    public UserDto mapToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setUsername(entity.getUsername());
        dto.setRealname(entity.getRealname());
        dto.setRole(entity.getRole().name());
        return dto;
    }

    // Convert our register DTO to an Entity.
    // Role is hardcoded as `USER` from enum.
    public UserEntity convertDtoToEntity(RegisterDto dto) {
        UserEntity user = new UserEntity();
        user.setUsername(dto.username());
        user.setRealname(dto.realname());
        user.setRole(UserRole.USER);
        return user;
    }

    // Register user to DB.
    public void registerUser(UserEntity user) { userRepository.save(user); }
}