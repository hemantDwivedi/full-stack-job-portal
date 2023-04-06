package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.ApplicantDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Applicant;
import com.inverse.project.Jobless.repositories.ApplicantRepository;
import com.inverse.project.Jobless.services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ModelMap modelMap;

    @Override
    public ApplicantDto createApplicant(ApplicantDto applicantDto) {
        Applicant applicant = this.modelMap.modelMapper().map(applicantDto, Applicant.class);
        applicant.setRole("APPLICANT");
        Applicant save = this.applicantRepository.save(applicant);
        return this.modelMap.modelMapper().map(save, ApplicantDto.class);
    }

    @Override
    public List<ApplicantDto> getAllApplicants() {
        List<Applicant> applicantList = this.applicantRepository.findAll();
        return applicantList
                .stream()
                .map(
                        applicant -> this.modelMap.modelMapper().map(applicant, ApplicantDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public ApplicantDto getById(int id) {
        Applicant applicant = this.applicantRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Applicant not found: ID " + id)
                );
        return this.modelMap.modelMapper().map(applicant, ApplicantDto.class);
    }

    @Override
    public ApplicantDto updateApplicant(ApplicantDto applicantDto, int id) {
        Applicant applicant = this.applicantRepository.findById(id)
        .orElseThrow(
                () -> new ResourceNotFoundException("Applicant not found: ID " + id)
        );
        applicant.setName(applicantDto.getName());
        applicant.setEmail(applicantDto.getEmail());
        applicant.setPassword(applicantDto.getPassword());
        this.applicantRepository.save(applicant);
        return this.modelMap.modelMapper().map(applicant, ApplicantDto.class);
    }

    @Override
    public void deleteApplicant(int id) {
        Applicant applicant = this.applicantRepository.findById(id)
                .orElseThrow(
                () -> new ResourceNotFoundException("Applicant not found: ID " + id)
        );
        this.applicantRepository.delete(applicant);
    }
}
