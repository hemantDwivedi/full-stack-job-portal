//package com.inverse.project.Jobless.services.impl;
//
//import com.inverse.project.Jobless.dto.UserDto;
//import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
//import com.inverse.project.Jobless.models.Role;
//import com.inverse.project.Jobless.models.User;
//import com.inverse.project.Jobless.repositories.RoleRepository;
//import com.inverse.project.Jobless.repositories.UserRepository;
//import com.inverse.project.Jobless.services.UserService;
//import lombok.AllArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class UserServiceImpl implements UserService {
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder;
//    private ModelMapper modelMapper;
//    @Override
//    public UserDto addUser(UserDto userDto, String roleName) {
//        User user = modelMapper.map(userDto, User.class);
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        String ROLE_OF_USER = "ROLE_" + roleName.toUpperCase();
//        Role role = roleRepository.findByName(ROLE_OF_USER);
//        if(role == null){
//            role = existRole(ROLE_OF_USER);
//        }
//        user.setRoles(List.of(role);
//        userRepository.save(user);
//        return modelMapper.map(user, UserDto.class);
//    }
//
//    @Override
//    public UserDto updateUser(UserDto userDto, Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("User not found id : " + id));
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        userRepository.save(user);
//        return modelMapper.map(user, UserDto.class);
//    }
//
//    @Override
//    public List<UserDto> listOfUser() {
//        List<User> users = userRepository.findAll();
//        return users
//                .stream()
//                .map(
//                        user -> modelMapper.map(user, UserDto.class)
//                ).collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("User not found id : " + id));
//        userRepository.delete(user);
//    }
//
//    private Role existRole(String roleName){
//        Role role = new Role();
//        role.setName("ROLE_" + roleName);
//        return roleRepository.save(role);
//    }
//}
