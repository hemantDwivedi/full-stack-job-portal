package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ResumeDto;

public interface ResumeService {
    ResumeDto create(ResumeDto resumeDto, Integer appliantId);
    ResumeDto getById(Integer id);
    ResumeDto update(ResumeDto resumeDto, Integer applicantId, Integer id);
    void delete(Integer applicantId, Integer id);
}
