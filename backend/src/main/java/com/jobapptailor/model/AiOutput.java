package com.jobapptailor.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "ai_outputs")
public class AiOutput {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tailored_bullets", columnDefinition = "TEXT")
    private List<String> tailoredBullets;
    
    @Column(name = "cover_letter", columnDefinition = "TEXT")
    private String coverLetter;
    
    @Column(columnDefinition = "TEXT")
    private String gaps;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "interview_questions", columnDefinition = "TEXT")
    private List<String> interviewQuestions;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "star_prompts", columnDefinition = "TEXT")
    private List<String> starPrompts;
    
    // Default constructor
    public AiOutput() {}
    
    // Constructor with parameters
    public AiOutput(Match match) {
        this.match = match;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Match getMatch() {
        return match;
    }
    
    public void setMatch(Match match) {
        this.match = match;
    }
    
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
}
