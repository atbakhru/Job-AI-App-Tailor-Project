package com.jobapptailor.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "job_posts")
public class JobPost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String source;
    
    @Column(unique = true)
    private String url;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "raw_json", columnDefinition = "TEXT")
    private Map<String, Object> rawJson;
    
    @Column(nullable = false, length = 500)
    private String title;
    
    @Column(nullable = false)
    private String company;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "skills", columnDefinition = "TEXT")
    private List<String> skills;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "responsibilities", columnDefinition = "TEXT")
    private List<String> responsibilities;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "qualifications", columnDefinition = "TEXT")
    private List<String> qualifications;
    
    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> matches;
    
    // Default constructor
    public JobPost() {}
    
    // Constructor with parameters
    public JobPost(String source, String url, String title, String company) {
        this.source = source;
        this.url = url;
        this.title = title;
        this.company = company;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Map<String, Object> getRawJson() {
        return rawJson;
    }
    
    public void setRawJson(Map<String, Object> rawJson) {
        this.rawJson = rawJson;
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
    
    public List<Match> getMatches() {
        return matches;
    }
    
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
