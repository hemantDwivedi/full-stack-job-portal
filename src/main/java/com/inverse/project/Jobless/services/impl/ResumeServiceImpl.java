package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.util.ValueMapper;
import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Applicant;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ApplicantRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ResumeService;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository repository;

    private final ApplicantRepository applicantRepository;
    private final ValueMapper valueMapper;

    public ResumeServiceImpl(ResumeRepository repository, ApplicantRepository applicantRepository, ValueMapper valueMapper) {
        this.repository = repository;
        this.applicantRepository = applicantRepository;
        this.valueMapper = valueMapper;
    }

    @Override
    public ResumeDto create(ResumeDto resumeDto, Integer applicantId) {
        Resume resume = this.valueMapper.modelMapper().map(resumeDto, Resume.class);
        Applicant applicant = this.applicantRepository.findById(applicantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Applicant not found: ID " + applicantId)
                );
        resume.setApplicant(applicant);
        this.repository.save(resume);
        return this.valueMapper.modelMapper().map(resume, ResumeDto.class);
    }
    @Override
    public ResumeDto getById(Integer id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        return this.valueMapper.modelMapper().map(resume, ResumeDto.class);
    }

    @Override
    public ResumeDto update(ResumeDto resumeDto,Integer applicantId, Integer id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        if (!resume.getApplicant().getId().equals(applicantId)) {
            throw new ResourceNotFoundException("Applicant Id not found: " + applicantId);
        }
        resume.setName(resumeDto.getName());
        resume.setEmail(resumeDto.getEmail());
        resume.setPhone(resumeDto.getPhone());
        resume.setAddress(resumeDto.getAddress());
        resume.setGender(resumeDto.getGender());
        resume.setLanguage(resumeDto.getLanguage());
        resume.setSkills(resumeDto.getSkills());
        resume.setLinks(resumeDto.getLinks());
        return this.valueMapper.modelMapper().map(this.repository.save(resume), ResumeDto.class);
    }

    @Override
    public void delete(Integer applicantId,Integer id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        if (!resume.getApplicant().getId().equals(applicantId)) {
            throw new ResourceNotFoundException("Applicant Id not found: " + applicantId);
        }
        this.repository.delete(resume);
    }
}
