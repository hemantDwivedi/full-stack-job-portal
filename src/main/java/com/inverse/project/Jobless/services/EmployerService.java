package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.EmployerDto;

import java.util.List;

public interface EmployerService {
    EmployerDto create(EmployerDto employerDto);
    EmployerDto update(EmployerDto employerDto, Integer id);
    EmployerDto getById(Integer id);
    List<EmployerDto> getAll();
    void delete(Integer id);
}
