package com.inverse.project.Jobless.repositories;

import com.inverse.project.Jobless.models.ApplicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDetailRepository extends JpaRepository<ApplicationDetails,Integer> {
}
