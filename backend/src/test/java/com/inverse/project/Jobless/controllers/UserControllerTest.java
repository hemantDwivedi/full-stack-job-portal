package com.inverse.project.Jobless.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.inverse.project.Jobless.dto.UserDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDto userDto;

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("testuser@test.com");
        userDto.setPassword(passwordEncoder.encode("12345"));
    }

    @Test
    @DisplayName("Add User API Test")
    public void testAddUser() {
        when(userService.addUser(any(UserDto.class), any(String.class))).thenReturn(userDto);
        ResponseEntity<UserDto> responseEntity = userController.addUser(userDto, "ROLE_USER");
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(userDto.getName(), responseEntity.getBody().getName());
    }

    @Test
    @DisplayName("Update User API Test")
    public void testUpdateUser() {
        Long userId = 1L;
        when(userService.updateUser(any(UserDto.class), any(Long.class))).thenReturn(userDto);
        ResponseEntity<UserDto> responseEntity = userController.updateUser(userDto, userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(userDto.getEmail(), responseEntity.getBody().getEmail());
    }

    @Test
    @DisplayName("List Of Users API Test")
    public void testListOfUsers() {
        List<UserDto> userDtoList = Arrays.asList(userDto);
        when(userService.listOfUser()).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> responseEntity = userController.listOfUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(userDto.getName(), responseEntity.getBody().get(0).getName());
    }

    @Test
    @DisplayName("Delete User API Test")
    public void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userService).deleteUser(userId);
        ResponseEntity<APIResponse> responseEntity = userController.deleteUser(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("User deleted id : " + userId, responseEntity.getBody().getMessage());
        verify(userService).deleteUser(userId);
    }
}
