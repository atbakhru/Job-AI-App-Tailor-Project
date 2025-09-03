package com.jobapptailor.dto;

import java.util.List;
import java.util.Map;

public class ResumeParseResponse {
    
    private Long resumeId;
    private String text;
    private List<String> extractedSkills;
    private List<Map<String, Object>> projects;
    private String message;
    
    // Default constructor
    public ResumeParseResponse() {}
    
    // Constructor with parameters
    public ResumeParseResponse(Long resumeId, String text, List<String> extractedSkills, String message) {
        this.resumeId = resumeId;
        this.text = text;
        this.extractedSkills = extractedSkills;
        this.message = message;
    }
    
    // Getters and Setters
    public Long getResumeId() {
        return resumeId;
    }
    
    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public List<String> getExtractedSkills() {
        return extractedSkills;
    }
    
    public void setExtractedSkills(List<String> extractedSkills) {
        this.extractedSkills = extractedSkills;
    }
    
    public List<Map<String, Object>> getProjects() {
        return projects;
    }
    
    public void setProjects(List<Map<String, Object>> projects) {
        this.projects = projects;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
