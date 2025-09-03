package com.jobapptailor.dto;

import java.util.List;

public class JobFetchResponse {
    
    private Long jobId;
    private String title;
    private String company;
    private String source;
    private List<String> skills;
    private List<String> responsibilities;
    private List<String> qualifications;
    private String message;
    
    // Default constructor
    public JobFetchResponse() {}
    
    // Constructor with parameters
    public JobFetchResponse(Long jobId, String title, String company, String source, String message) {
        this.jobId = jobId;
        this.title = title;
        this.company = company;
        this.source = source;
        this.message = message;
    }
    
    // Getters and Setters
    public Long getJobId() {
        return jobId;
    }
    
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public List<String> getSkills() {
        return skills;
    }
    
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    
    public List<String> getResponsibilities() {
        return responsibilities;
    }
    
    public void setResponsibilities(List<String> responsibilities) {
        this.responsibilities = responsibilities;
    }
    
    public List<String> getQualifications() {
        return qualifications;
    }
    
    public void setQualifications(List<String> qualifications) {
        this.qualifications = qualifications;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
