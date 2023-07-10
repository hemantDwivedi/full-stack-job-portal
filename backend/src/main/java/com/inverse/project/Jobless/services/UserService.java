package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto, String roleName);
    UserDto updateUser(UserDto userDto, Long id);
    List<UserDto> listOfUser();
    void deleteUser(Long id);
}
