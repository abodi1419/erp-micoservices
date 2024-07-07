package com.later.companyservice.App.company.HR.jobTitles.service;

import com.later.companyservice.App.company.HR.jobTitles.entity.JobTitle;
import com.later.companyservice.App.company.HR.jobTitles.repository.JobTitleRepo;
import com.later.companyservice.Exception.ApiException;
import com.later.companyservice.constants.ResponseText;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleService {
    private final JobTitleRepo jobTitleRepo;

    public List<JobTitle> getJobTitles() {
        return jobTitleRepo.findAll();
    }

    public JobTitle getById(Long id) throws ApiException {
        JobTitle jobTitle = jobTitleRepo.findById(id).orElse(null);
        if (jobTitle == null) {
            throw new ApiException(400, "Job title not found");
        }
        return jobTitle;
    }

    public String create(JobTitle jobTitle) {
        jobTitle.setId(null);
        jobTitle.setEmployees(null);
        jobTitleRepo.save(jobTitle);
        return "Created Successfully";
    }

    public String update(Long id, JobTitle jobTitle) throws ApiException {
        JobTitle jobTitle1 = jobTitleRepo.findById(id).orElse(null);
        if (jobTitle1 == null) {
            throw new ApiException(404, "Job Title Not Found");
        }
        jobTitle1.setName(jobTitle.getName());
        jobTitle1.setNameAr(jobTitle.getNameAr());
        jobTitle1.setDescription(jobTitle.getDescription());
        jobTitle1.setDescriptionAr(jobTitle.getDescriptionAr());
        jobTitleRepo.save(jobTitle1);
        return ResponseText.UPDATED.text();
    }
}
