package com.jobapptailor.service;

import com.jobapptailor.dto.MatchRequest;
import com.jobapptailor.dto.MatchResponse;
import com.jobapptailor.model.JobPost;
import com.jobapptailor.model.Match;
import com.jobapptailor.model.Resume;
import com.jobapptailor.model.User;
import com.jobapptailor.repository.JobPostRepository;
import com.jobapptailor.repository.MatchRepository;
import com.jobapptailor.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private ResumeRepository resumeRepository;
    
    @Autowired
    private JobPostRepository jobPostRepository;
    
    public MatchResponse createMatch(MatchRequest request) throws Exception {
        // Find resume and job post
        Optional<Resume> resumeOpt = resumeRepository.findById(request.getResumeId());
        Optional<JobPost> jobPostOpt = jobPostRepository.findById(request.getJobId());
        
        if (resumeOpt.isEmpty()) {
            throw new Exception("Resume not found");
        }
        if (jobPostOpt.isEmpty()) {
            throw new Exception("Job post not found");
        }
        
        Resume resume = resumeOpt.get();
        JobPost jobPost = jobPostOpt.get();
        User user = resume.getUser();
        
        // Calculate match score
        int score = calculateMatchScore(resume, jobPost);
        
        // Find missing skills
        List<String> missingSkills = findMissingSkills(resume, jobPost);
        
        // Create and save match
        Match match = new Match(user, resume, jobPost, score);
        match.setMissingSkills(missingSkills);
        match = matchRepository.save(match);
        
        MatchResponse response = new MatchResponse(
            match.getId(),
            resume.getId(),
            jobPost.getId(),
            score,
            "Match created successfully"
        );
        
        response.setMissingSkills(missingSkills);
        return response;
    }
    
    private int calculateMatchScore(Resume resume, JobPost jobPost) {
        if (resume.getExtractedSkills() == null || jobPost.getSkills() == null) {
            return 50; // Default score
        }
        
        List<String> resumeSkills = resume.getExtractedSkills();
        List<String> jobSkills = jobPost.getSkills();
        
        if (jobSkills.isEmpty()) {
            return 75; // No specific requirements
        }
        
        long matchingSkills = jobSkills.stream()
            .filter(jobSkill -> resumeSkills.stream()
                .anyMatch(resumeSkill -> resumeSkill.toLowerCase().contains(jobSkill.toLowerCase())))
            .count();
        
        double scoreRatio = (double) matchingSkills / jobSkills.size();
        return (int) Math.round(scoreRatio * 100);
    }
    
    private List<String> findMissingSkills(Resume resume, JobPost jobPost) {
        if (resume.getExtractedSkills() == null || jobPost.getSkills() == null) {
            return new ArrayList<>();
        }
        
        List<String> resumeSkills = resume.getExtractedSkills();
        List<String> jobSkills = jobPost.getSkills();
        
        return jobSkills.stream()
            .filter(jobSkill -> resumeSkills.stream()
                .noneMatch(resumeSkill -> resumeSkill.toLowerCase().contains(jobSkill.toLowerCase())))
            .toList();
    }
}
