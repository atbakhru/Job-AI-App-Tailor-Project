package com.jobapptailor.dto;

import java.util.List;

public class MatchResponse {
    
    private Long id;
    private Long resumeId;
    private Long jobId;
    private Integer score;
    private List<String> missingSkills;
    private String message;
    
    // Default constructor
    public MatchResponse() {}
    
    // Constructor with parameters
    public MatchResponse(Long id, Long resumeId, Long jobId, Integer score, String message) {
        this.id = id;
        this.resumeId = resumeId;
        this.jobId = jobId;
        this.score = score;
        this.message = message;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public Integer getScore() {
        return score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }
    
    public List<String> getMissingSkills() {
        return missingSkills;
    }
    
    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
