package com.jobapptailor.dto;

import org.springframework.web.multipart.MultipartFile;

public class ResumeParseRequest {
    
    private Long userId;
    private MultipartFile file;
    private String text;
    
    // Default constructor
    public ResumeParseRequest() {}
    
    // Getters and Setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public MultipartFile getFile() {
        return file;
    }
    
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
