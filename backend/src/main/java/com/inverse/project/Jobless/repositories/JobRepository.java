package com.inverse.project.Jobless.repositories;

import com.inverse.project.Jobless.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByName(String name);
    List<Job> findByLocation(String location);
    List<Job> findByCompanyName(String name);
}
