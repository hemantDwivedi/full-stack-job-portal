package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.models.Role;
import com.inverse.project.Jobless.util.ValueMapper;
import com.inverse.project.Jobless.dto.EmployerDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Employer;
import com.inverse.project.Jobless.repositories.EmployerRepository;
import com.inverse.project.Jobless.services.EmployerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    private final ValueMapper valueMapper;

    public EmployerServiceImpl(EmployerRepository employerRepository, ValueMapper valueMapper) {
        this.employerRepository = employerRepository;
        this.valueMapper = valueMapper;
    }

    // create employer
    @Override
    public EmployerDto create(EmployerDto employerDto) {
        Employer employer = this.valueMapper.modelMapper().map(employerDto, Employer.class);
        employer.setRole(Role.ROLE_EMPLOYER.toString());
        this.employerRepository.save(employer);
        return this.valueMapper.modelMapper().map(employer, EmployerDto.class);
    }

    @Override
    public EmployerDto update(EmployerDto employerDto, Integer id) {
        Employer employer = this.employerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + id)
                );
        employer.setName(employerDto.getName());
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setContact(employerDto.getContact());
        this.employerRepository.save(employer);
        return this.valueMapper.modelMapper().map(employer, EmployerDto.class);
    }

    @Override
    public EmployerDto getById(Integer id) {
        Employer employer = this.employerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + id)
                );
        return this.valueMapper.modelMapper().map(employer, EmployerDto.class);
    }

    // fetch all employer
    @Override
    public List<EmployerDto> getAll() {
        List<Employer> employerList = this.employerRepository.findAll();
        return employerList
                .stream()
                .map(
                        employer -> this.valueMapper.modelMapper().map(employer, EmployerDto.class)
                ).collect(Collectors.toList());
    }

    // delete a employer
    @Override
    public void delete(Integer id) {
        Employer employer = this.employerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + id)
                );
        this.employerRepository.delete(employer);
    }
}
