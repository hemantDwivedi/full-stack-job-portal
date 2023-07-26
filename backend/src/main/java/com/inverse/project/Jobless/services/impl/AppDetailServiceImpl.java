package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.dto.ApplicationDetailsDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.ApplicationDetails;
import com.inverse.project.Jobless.models.Job;
import com.inverse.project.Jobless.repositories.ApplicationDetailRepository;
import com.inverse.project.Jobless.repositories.JobRepository;
import com.inverse.project.Jobless.services.AppDetailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppDetailServiceImpl implements AppDetailService {

    private final ApplicationDetailRepository detailRepository;
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    // create application details
    @Override
    public ApplicationDetailsDto create(ApplicationDetailsDto detailsDto, Integer jodId) {
        ApplicationDetails application = modelMapper.map(detailsDto, ApplicationDetails.class);
        Job job = this.jobRepository.findById(jodId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Job not found ID: " + jodId)
                        );
        application.setJob(job);
        this.detailRepository.save(application);
        return modelMapper.map(application, ApplicationDetailsDto.class);
    }

    // udpate application details
    @Override
    public ApplicationDetailsDto update(ApplicationDetailsDto detailsDto,
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
        application.setStatus(detailsDto.getStatus());
        application.setJob(job);
        this.detailRepository.save(application);
        return modelMapper.map(application, ApplicationDetailsDto.class);
    }

    // delete specific application details
    @Override
    public void delete(Integer id) {
        ApplicationDetails applicationDetails = this.detailRepository.findById(id)
                .orElseThrow(
                        ()  -> new ResourceNotFoundException("Application Details not found ID: " + id)
                );
        this.detailRepository.delete(applicationDetails);
    }
}
