package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository repository;
    @Autowired
    private ModelMap modelMap;
    @Override
    public ResumeDto create(ResumeDto resumeDto) {
        Resume resume = this.modelMap.modelMapper().map(resumeDto, Resume.class);
        this.repository.save(resume);
        return this.modelMap.modelMapper().map(resume, ResumeDto.class);
    }
    @Override
    public ResumeDto getById(int id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        return this.modelMap.modelMapper().map(resume, ResumeDto.class);
    }

    @Override
    public ResumeDto update(ResumeDto resumeDto, int id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        resume.setName(resumeDto.getName());
        resume.setEmail(resumeDto.getEmail());
        resume.setPhone(resumeDto.getPhone());
        resume.setAddress(resumeDto.getAddress());
        resume.setGender(resumeDto.getGender());
        resume.setLanguage(resumeDto.getLanguage());
        resume.setSkills(resumeDto.getSkills());
        resume.setLinks(resumeDto.getLinks());
        return this.modelMap.modelMapper().map(this.repository.save(resume), ResumeDto.class);
    }

    @Override
    public void delete(int id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        this.repository.delete(resume);
    }
}
