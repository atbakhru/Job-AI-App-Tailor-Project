package com.jobapptailor.dto;

public class JobFetchRequest {
    
    private String url;
    private String text;
    
    // Default constructor
    public JobFetchRequest() {}
    
    // Constructor with parameters
    public JobFetchRequest(String url, String text) {
        this.url = url;
        this.text = text;
    }
    
    // Getters and Setters
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
