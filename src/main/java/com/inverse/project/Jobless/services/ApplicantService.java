package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ApplicantDto;

import java.util.List;

public interface ApplicantService {
    ApplicantDto createApplicant(ApplicantDto applicantDto);
    List<ApplicantDto> getAllApplicants();
    ApplicantDto getById(Integer id);
    ApplicantDto updateApplicant(ApplicantDto applicantDto, Integer id);
    void deleteApplicant(Integer id);
}
