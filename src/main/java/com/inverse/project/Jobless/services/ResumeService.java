package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ResumeDto;

public interface ResumeService {
    ResumeDto create(ResumeDto resumeDto);
    ResumeDto getById(int id);
    ResumeDto update(ResumeDto resumeDto, int id);
    void delete(int id);
}
