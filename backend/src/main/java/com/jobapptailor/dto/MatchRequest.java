package com.jobapptailor.dto;

public class MatchRequest {
    
    private Long resumeId;
    private Long jobId;
    
    // Default constructor
    public MatchRequest() {}
    
    // Constructor with parameters
    public MatchRequest(Long resumeId, Long jobId) {
        this.resumeId = resumeId;
        this.jobId = jobId;
    }
    
    // Getters and Setters
    public Long getResumeId() {
        return resumeId;
    }
    
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    
    public Long getJobId() {
        return jobId;
    }
    
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
