package com.jobapptailor.dto;

import java.util.List;

public class AiTailorResponse {
    
    private List<String> tailoredBullets;
    private String coverLetter;
    private String gaps;
    private List<String> interviewQuestions;
    private List<String> starPrompts;
    private String message;
    
    // Default constructor
    public AiTailorResponse() {}
    
    // Constructor with parameters
    public AiTailorResponse(String message) {
        this.message = message;
    }
    
    // Getters and Setters
    public List<String> getTailoredBullets() {
        return tailoredBullets;
    }
    
    public void setTailoredBullets(List<String> tailoredBullets) {
        this.tailoredBullets = tailoredBullets;
    }
    
    public String getCoverLetter() {
        return coverLetter;
    }
    
    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
    
    public String getGaps() {
        return gaps;
    }
    
    public void setGaps(String gaps) {
        this.gaps = gaps;
    }
    
    public List<String> getInterviewQuestions() {
        return interviewQuestions;
    }
    
    public void setInterviewQuestions(List<String> interviewQuestions) {
        this.interviewQuestions = interviewQuestions;
    }
    
    public List<String> getStarPrompts() {
        return starPrompts;
    }
    
    public void setStarPrompts(List<String> starPrompts) {
        this.starPrompts = starPrompts;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
