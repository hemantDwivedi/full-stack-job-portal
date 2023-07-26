package com.xyz.services;

import com.xyz.dto.AdminDto;

public interface AdminService {
    AdminDto create(AdminDto adminDto);
    AdminDto update(AdminDto adminDto, Integer id);
    AdminDto getById(Integer id);
    void delete(Integer id);
}
