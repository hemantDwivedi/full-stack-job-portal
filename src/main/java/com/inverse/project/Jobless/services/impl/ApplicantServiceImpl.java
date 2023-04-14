package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.models.Role;
import com.inverse.project.Jobless.util.ValueMapper;
import com.inverse.project.Jobless.dto.ApplicantDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Applicant;
import com.inverse.project.Jobless.repositories.ApplicantRepository;
import com.inverse.project.Jobless.services.ApplicantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    private final ValueMapper valueMapper;

    public ApplicantServiceImpl(ApplicantRepository applicantRepository, ValueMapper valueMapper) {
        this.applicantRepository = applicantRepository;
        this.valueMapper = valueMapper;
    }

    @Override
    public ApplicantDto createApplicant(ApplicantDto applicantDto) {
        Applicant applicant = this.valueMapper.modelMapper().map(applicantDto, Applicant.class);
        applicant.setRole(Role.ROLE_APPLICANT.toString());
        Applicant save = this.applicantRepository.save(applicant);
        return this.valueMapper.modelMapper().map(save, ApplicantDto.class);
    }

    @Override
    public List<ApplicantDto> getAllApplicants() {
        List<Applicant> applicantList = this.applicantRepository.findAll();
        return applicantList
                .stream()
                .map(
                        applicant -> this.valueMapper.modelMapper().map(applicant, ApplicantDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public ApplicantDto getById(Integer id) {
        Applicant applicant = this.applicantRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Applicant not found: ID " + id)
                );
        return this.valueMapper.modelMapper().map(applicant, ApplicantDto.class);
    }

    @Override
    public ApplicantDto updateApplicant(ApplicantDto applicantDto, Integer id) {
        Applicant applicant = this.applicantRepository.findById(id)
        .orElseThrow(
                () -> new ResourceNotFoundException("Applicant not found: ID " + id)
        );
        applicant.setName(applicantDto.getName());
        applicant.setEmail(applicantDto.getEmail());
        applicant.setPassword(applicantDto.getPassword());
        this.applicantRepository.save(applicant);
        return this.valueMapper.modelMapper().map(applicant, ApplicantDto.class);
    }

    @Override
    public void deleteApplicant(Integer id) {
        Applicant applicant = this.applicantRepository.findById(id)
                .orElseThrow(
                () -> new ResourceNotFoundException("Applicant not found: ID " + id)
        );
        this.applicantRepository.delete(applicant);
    }
}
