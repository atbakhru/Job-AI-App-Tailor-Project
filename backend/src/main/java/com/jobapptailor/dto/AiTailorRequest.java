package com.jobapptailor.dto;

public class AiTailorRequest {
    
    private Long matchId;
    private String styleHints;
    
    // Default constructor
    public AiTailorRequest() {}
    
    // Constructor with parameters
    public AiTailorRequest(Long matchId, String styleHints) {
        this.matchId = matchId;
        this.styleHints = styleHints;
    }
    
    // Getters and Setters
    public Long getMatchId() {
        return matchId;
    }
    
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    
    public String getStyleHints() {
        return styleHints;
    }
    
    public void setStyleHints(String styleHints) {
        this.styleHints = styleHints;
    }
}
