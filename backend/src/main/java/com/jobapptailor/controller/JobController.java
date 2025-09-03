package com.jobapptailor.controller;

import com.jobapptailor.dto.JobFetchRequest;
import com.jobapptailor.dto.JobFetchResponse;
import com.jobapptailor.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    @PostMapping("/fetch")
    public ResponseEntity<JobFetchResponse> fetchJob(@RequestBody JobFetchRequest request) {
        try {
            JobFetchResponse response = jobService.fetchJobData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
