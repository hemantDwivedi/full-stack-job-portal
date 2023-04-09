package com.inverse.project.Jobless.services.impl;

import com.fasterxml.jackson.core.Base64Variant;
import com.inverse.project.Jobless.config.JwtService;
import com.inverse.project.Jobless.models.Admin;
import com.inverse.project.Jobless.repositories.AdminRepository;
import com.inverse.project.Jobless.security.AuthenticationRequest;
import com.inverse.project.Jobless.security.AuthenticationResponse;
import com.inverse.project.Jobless.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoders;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){

        var admin = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoders.encode(request.getPassword()))
                .role("ADMIN")
                .build();

        adminRepository.save(admin);

        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .TOKEN(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder()
                .TOKEN(jwtToken)
                .build();
    }
}
