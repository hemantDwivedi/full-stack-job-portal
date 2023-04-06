package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ApplicantDto;

import java.util.List;

public interface ApplicantService {
    ApplicantDto createApplicant(ApplicantDto applicantDto);
    List<ApplicantDto> getAllApplicants();
    ApplicantDto getById(int id);
    ApplicantDto updateApplicant(ApplicantDto applicantDto, int id);
    void deleteApplicant(int id);
}
