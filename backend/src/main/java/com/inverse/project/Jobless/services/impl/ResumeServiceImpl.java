package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ResumeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repository;
    private ModelMapper modelMapper;

    @Override
    public ResumeDto create(ResumeDto resumeDto) {
        Resume resume = modelMapper.map(resumeDto, Resume.class);
        this.repository.save(resume);
        return modelMapper.map(resume, ResumeDto.class);
    }
    @Override
    public ResumeDto getById(Integer id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        return modelMapper.map(resume, ResumeDto.class);
    }

    @Override
    public ResumeDto update(ResumeDto resumeDto, Integer id) {
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
        return modelMapper.map(this.repository.save(resume), ResumeDto.class);
    }

    @Override
    public void delete(Integer id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        this.repository.delete(resume);
    }
}
