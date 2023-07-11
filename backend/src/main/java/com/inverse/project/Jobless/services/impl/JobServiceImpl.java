package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.dto.JobDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Job;
import com.inverse.project.Jobless.models.JobCategory;
import com.inverse.project.Jobless.repositories.JobCategoryRepository;
import com.inverse.project.Jobless.repositories.JobRepository;
import com.inverse.project.Jobless.services.JobService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobCategoryRepository jobCategoryRepository;
    private ModelMapper modelMapper;


    @Override
    public JobDto create(JobDto jobDto, Integer jobCategoryId) {
        Job job = modelMapper.map(jobDto, Job.class);
        JobCategory jobCategory = this.jobCategoryRepository.findById(jobCategoryId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job Category not found ID: " + jobCategoryId)
                );
        job.setJobCategory(jobCategory);
        this.jobRepository.save(job);
        return modelMapper.map(job, JobDto.class);
    }

    @Override
    public JobDto update(JobDto jobDto,Integer jobCategoryId, Integer id) {
        Job job = this.jobRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job not found ID: " + id)
                );
        JobCategory jobCategory = this.jobCategoryRepository.findById(jobCategoryId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Job Category not found ID: " + jobCategoryId)
                        );
        job.setName(jobDto.getName());
        job.setAbout(jobDto.getAbout());
        job.setLocation(jobDto.getLocation());
        job.setStartDate(jobDto.getStartDate());
        job.setApplyBy(jobDto.getApplyBy());
        job.setSkillList(jobDto.getSkillList());
        job.setNumberOfOpening(jobDto.getNumberOfOpening());
        job.setSalary(jobDto.getSalary());
        job.setCompanyName(jobDto.getCompanyName());
        job.setCompanyAbout(jobDto.getCompanyAbout());
        job.setCompanyWebsite(jobDto.getCompanyWebsite());
        job.setJobCategory(jobCategory);
        this.jobRepository.save(job);
        return modelMapper.map(job, JobDto.class);
    }

    @Override
    public JobDto getById(Integer id) {
        Job job = this.jobRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job not found ID: " + id)
                );
        return modelMapper.map(job, JobDto.class);
    }

    @Override
    public List<JobDto> getAll() {
        List<Job> jobs = this.jobRepository.findAll();
        return jobs
                .stream()
                .map(
                        job ->  modelMapper.map(job, JobDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public List<JobDto> searchByName(String name) {
        List<Job> jobs = jobRepository.findByName(name);
        if (jobs.get(0).getName() == null){
            throw new ResourceNotFoundException("Job not found");
        }
        return jobs.stream().map(job -> modelMapper.map(job, JobDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobDto> searchByLocation(String location) {
        List<Job> jobs = jobRepository.findByLocation(location);
        if (jobs.get(0).getName() == null){
            throw new ResourceNotFoundException("Job not found");
        }
        return jobs.stream().map(job -> modelMapper.map(job, JobDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<JobDto> searchByComanyName(String companyName) {
        List<Job> jobs = jobRepository.findByCompanyName(companyName);
        if (jobs.get(0).getName() == null){
            throw new ResourceNotFoundException("Job not found");
        }
        return jobs.stream().map(job -> modelMapper.map(job, JobDto.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        Job job = this.jobRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job not found ID: " + id)
                );
        this.jobRepository.delete(job);
    }
}
