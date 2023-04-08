package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Applicant;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ApplicantRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeRepository repository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private ModelMap modelMap;
    @Override
    public ResumeDto create(ResumeDto resumeDto, Integer applicantId) {
        Resume resume = this.modelMap.modelMapper().map(resumeDto, Resume.class);
        Applicant applicant = this.applicantRepository.findById(applicantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Applicant not found: ID " + applicantId)
                );
        resume.setApplicant(applicant);
        this.repository.save(resume);
        return this.modelMap.modelMapper().map(resume, ResumeDto.class);
    }
    @Override
    public ResumeDto getById(Integer id) {
        Resume resume = this.repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found: ID " + id)
                );
        return this.modelMap.modelMapper().map(resume, ResumeDto.class);
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
        return this.modelMap.modelMapper().map(this.repository.save(resume), ResumeDto.class);
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
