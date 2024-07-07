package com.later.companyservice.App.company.HR.jobTitles.controller;

import com.later.companyservice.App.company.HR.jobTitles.entity.JobTitle;
import com.later.companyservice.App.company.HR.jobTitles.service.JobTitleService;
import com.later.companyservice.constants.ApiResponse;
import com.later.companyservice.Exception.ApiException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("api/v1/job-titles")
@RequiredArgsConstructor
public class JobTitleController {

    private final JobTitleService jobTitleService;


    @GetMapping("")
    public ResponseEntity getJobTitles() {
        return ok().body(new ApiResponse(true, 200, "Success", jobTitleService.getJobTitles()));
    }

    @GetMapping("{id}")
    public ResponseEntity getJobTitle(@PathVariable Long id) throws ApiException {
        return ok().body(new ApiResponse(true, 200, "Success", jobTitleService.getById(id)));
    }

    @PostMapping("create")
    public ResponseEntity createJobTitle(@Valid @RequestBody JobTitle jobTitle) {
        return ok().body(new ApiResponse(true, 200, "Success", jobTitleService.create(jobTitle)));
    }

    @PostMapping("update/{id}")
    public ResponseEntity updateJobTitle(@Valid @RequestBody JobTitle jobTitle, @PathVariable Long id) throws ApiException {
        return ok().body(new ApiResponse(true, 200, "Success", jobTitleService.update(id, jobTitle)));

    }

}
