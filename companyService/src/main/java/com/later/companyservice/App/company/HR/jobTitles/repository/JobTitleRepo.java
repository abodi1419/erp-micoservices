package com.later.companyservice.App.company.HR.jobTitles.repository;

import com.later.companyservice.App.company.HR.jobTitles.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobTitleRepo extends JpaRepository<JobTitle, Long> {
}
