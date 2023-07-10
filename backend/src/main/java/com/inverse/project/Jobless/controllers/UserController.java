package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.UserDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto,
                                           @RequestParam("role") String roleName){
        return new ResponseEntity<>(userService.addUser(userDto,roleName), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto,
                                           @PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(userDto,id));
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> listOfUsers(){
        return ResponseEntity.ok(userService.listOfUser());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new APIResponse("User deleted id : " + id));
    }
}
