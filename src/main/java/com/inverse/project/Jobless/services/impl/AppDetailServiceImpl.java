package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.util.ValueMapper;
import com.inverse.project.Jobless.dto.ApplicationDetailsDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Applicant;
import com.inverse.project.Jobless.models.ApplicationDetails;
import com.inverse.project.Jobless.models.Job;
import com.inverse.project.Jobless.repositories.ApplicantRepository;
import com.inverse.project.Jobless.repositories.ApplicationDetailRepository;
import com.inverse.project.Jobless.repositories.JobRepository;
import com.inverse.project.Jobless.services.AppDetailService;
import org.springframework.stereotype.Service;

@Service
public class AppDetailServiceImpl implements AppDetailService {

    private final ApplicationDetailRepository detailRepository;
    private final ApplicantRepository applicantRepository;

    private final JobRepository jobRepository;
    private final ValueMapper valueMapper;

    public AppDetailServiceImpl(ApplicationDetailRepository detailRepository, ApplicantRepository applicantRepository, JobRepository jobRepository, ValueMapper valueMapper) {
        this.detailRepository = detailRepository;
        this.applicantRepository = applicantRepository;
        this.jobRepository = jobRepository;
        this.valueMapper = valueMapper;
    }

    // create application details
    @Override
    public ApplicationDetailsDto create(ApplicationDetailsDto detailsDto, Integer applicantId, Integer jodId) {
        ApplicationDetails application = this.valueMapper.modelMapper().map(detailsDto, ApplicationDetails.class);
        Applicant applicant = this.applicantRepository.findById(applicantId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Applicant Id not found: " + applicantId)
                        );
        Job job = this.jobRepository.findById(jodId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Job not found ID: " + jodId)
                        );
        application.setApplicant(applicant);
        application.setJob(job);
        this.detailRepository.save(application);
        return this.valueMapper.modelMapper().map(applicant, ApplicationDetailsDto.class);
    }

    // udpate application details
    @Override
    public ApplicationDetailsDto update(ApplicationDetailsDto detailsDto, Integer applicantId,
                                        Integer jobId,
                                        Integer id){
        ApplicationDetails application = this.detailRepository.findById(id)
                .orElseThrow(
                        ()  -> new ResourceNotFoundException("Application Details not found ID: " + id)
                );
        Job job = this.jobRepository.findById(jobId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job not found ID: " + jobId)
                );
        if (!application.getApplicant().getId().equals(applicantId)) {
            throw new ResourceNotFoundException("Applicant Id not found: " + applicantId);
        }
        application.setStatus(detailsDto.getStatus());
        application.setJob(job);
        this.detailRepository.save(application);
        return this.valueMapper.modelMapper().map(application, ApplicationDetailsDto.class);
    }

    // delete specific application details
    @Override
    public void delete(Integer applicantId, Integer id) {
        ApplicationDetails applicationDetails = this.detailRepository.findById(id)
                .orElseThrow(
                        ()  -> new ResourceNotFoundException("Application Details not found ID: " + id)
                );

        if (!applicationDetails.getApplicant().getId().equals(applicantId)) {
            throw new ResourceNotFoundException("Applicant Id not found: " + applicantId);
        }
        this.detailRepository.delete(applicationDetails);
    }
}