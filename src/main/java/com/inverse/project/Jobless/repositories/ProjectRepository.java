package com.inverse.project.Jobless.repositories;

import com.inverse.project.Jobless.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
