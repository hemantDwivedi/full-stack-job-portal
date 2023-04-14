package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.models.Role;
import com.inverse.project.Jobless.util.ValueMapper;
import com.inverse.project.Jobless.dto.AdminDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Admin;
import com.inverse.project.Jobless.repositories.AdminRepository;
import com.inverse.project.Jobless.services.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ValueMapper valueMapper;

    public AdminServiceImpl(AdminRepository adminRepository, ValueMapper valueMapper) {
        this.adminRepository = adminRepository;
        this.valueMapper = valueMapper;
    }

    @Override
    public AdminDto create(AdminDto adminDto) {
        Admin admin = this.valueMapper.modelMapper().map(adminDto, Admin.class);
        admin.setRole(Role.ROLE_ADMIN.toString());
        this.adminRepository.save(admin);
        return this.valueMapper.modelMapper().map(admin, AdminDto.class);
    }

    @Override
    public AdminDto update(AdminDto adminDto, Integer id) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Admin not found ID: " + id)
                );
        admin.setEmail(adminDto.getEmail());
        admin.setName(adminDto.getName());
        admin.setPassword(admin.getPassword());
        this.adminRepository.save(admin);
        return this.valueMapper.modelMapper().map(admin, AdminDto.class);
    }

    @Override
    public AdminDto getById(Integer id) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Admin not found ID: " + id)
                );
        return this.valueMapper.modelMapper().map(admin, AdminDto.class);
    }

    @Override
    public void delete(Integer id) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Admin not found ID: " + id)
                );
        this.adminRepository.delete(admin);
    }
}
