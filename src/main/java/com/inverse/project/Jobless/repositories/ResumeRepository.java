package com.inverse.project.Jobless.repositories;

import com.inverse.project.Jobless.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}
