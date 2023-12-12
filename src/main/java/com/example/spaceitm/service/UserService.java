package com.example.spaceitm.service;


import com.example.spaceitm.dto.UserDto;
import com.example.spaceitm.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UserService {

    User save(UserDto userDto);

    User getCurrentUser();

    void updateUser(User user);
}
