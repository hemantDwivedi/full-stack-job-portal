package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ResumeDto;

public interface ResumeService {
    ResumeDto create(ResumeDto resumeDto);
    ResumeDto getById(Integer id);
    ResumeDto update(ResumeDto resumeDto, Integer id);
    void delete(Integer id);
}