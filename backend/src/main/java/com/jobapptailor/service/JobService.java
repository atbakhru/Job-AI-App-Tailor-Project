package com.jobapptailor.service;

import com.jobapptailor.dto.JobFetchRequest;
import com.jobapptailor.dto.JobFetchResponse;
import com.jobapptailor.model.JobPost;
import com.jobapptailor.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class JobService {
    
    @Autowired
    private JobPostRepository jobPostRepository;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    public JobFetchResponse fetchJobData(JobFetchRequest request) throws Exception {
        String source = "manual_input";
        String title;
        String company;
        String jobText;
        
        if (request.getText() != null && !request.getText().trim().isEmpty()) {
            // Use provided text
            jobText = request.getText();
            title = extractTitleFromText(jobText);
            company = extractCompanyFromText(jobText);
        } else {
            throw new Exception("Job description text is required");
        }
        
        // Check if job already exists (by content hash or similar identifier)
        Optional<JobPost> existingJob = Optional.empty(); // We'll skip URL-based lookup
        JobPost jobPost;
        
        if (existingJob.isPresent()) {
            jobPost = existingJob.get();
        } else {
            // Create new job post
            jobPost = new JobPost(source, request.getUrl(), title, company);
            
            // Extract skills, responsibilities, and qualifications
            List<String> skills = extractSkills(jobText);
            List<String> responsibilities = extractResponsibilities(jobText);
            List<String> qualifications = extractQualifications(jobText);
            
            jobPost.setSkills(skills);
            jobPost.setResponsibilities(responsibilities);
            jobPost.setQualifications(qualifications);
            
            jobPost = jobPostRepository.save(jobPost);
        }
        
        JobFetchResponse response = new JobFetchResponse(
            jobPost.getId(),
            jobPost.getTitle(),
            jobPost.getCompany(),
            jobPost.getSource(),
            "Job data fetched successfully"
        );
        
        response.setSkills(jobPost.getSkills());
        response.setResponsibilities(jobPost.getResponsibilities());
        response.setQualifications(jobPost.getQualifications());
        
        return response;
    }
    
    private String extractTitleFromText(String text) {
        // Look for common job title patterns
        String[] lines = text.split("\\n");
        for (String line : lines) {
            line = line.trim();
            // Check if line contains job-related keywords and is reasonably short
            if ((line.toLowerCase().contains("engineer") || 
                line.toLowerCase().contains("developer") ||
                line.toLowerCase().contains("manager") ||
                line.toLowerCase().contains("analyst") ||
                line.toLowerCase().contains("specialist") ||
                line.toLowerCase().contains("coordinator")) &&
                line.length() <= 100) {
                return truncateTitle(line);
            }
        }
        
        // Look for lines that might be titles (shorter lines, not full paragraphs)
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && line.length() < 100 && !line.contains(".")) {
                return truncateTitle(line);
            }
        }
        
        // If still no title found, look for any line mentioning common job words
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && 
                (line.toLowerCase().contains("position") ||
                 line.toLowerCase().contains("role") ||
                 line.toLowerCase().contains("job") ||
                 line.toLowerCase().contains("opening"))) {
                return truncateTitle(line);
            }
        }
        
        return "Software Engineer";
    }
    
    private String truncateTitle(String title) {
        if (title.length() > 200) {
            return title.substring(0, 200) + "...";
        }
        return title;
    }
    
    private String extractCompanyFromText(String text) {
        // Simple pattern to find company name - look for first short line or common company patterns
        String[] lines = text.split("\\n");
        
        // Look for lines that might contain company names
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && line.length() < 100) {
                // Check if it looks like a company name (contains common company suffixes)
                if (line.toLowerCase().contains("inc") ||
                    line.toLowerCase().contains("corp") ||
                    line.toLowerCase().contains("llc") ||
                    line.toLowerCase().contains("ltd") ||
                    line.toLowerCase().contains("company") ||
                    line.toLowerCase().matches(".*\\b(bank|group|systems|technologies|solutions)\\b.*")) {
                    return line.length() > 100 ? line.substring(0, 100) : line;
                }
            }
        }
        
        // Fallback: use first non-empty short line
        for (String line : lines) {
            line = line.trim();
            if (!line.isEmpty() && line.length() < 50 && !line.contains(".")) {
                return line;
            }
        }
        
        return "Tech Company";
    }
    
    private List<String> extractSkills(String text) {
        String[] techSkills = {
            "Java", "Python", "JavaScript", "TypeScript", "React", "Angular", "Vue",
            "Spring Boot", "Node.js", "Express", "Django", "Flask", "PostgreSQL",
            "MySQL", "MongoDB", "Redis", "Docker", "Kubernetes", "AWS", "Azure",
            "Git", "Jenkins", "CI/CD", "REST", "GraphQL", "Microservices"
        };
        
        return Arrays.stream(techSkills)
            .filter(skill -> containsSkill(text, skill))
            .collect(Collectors.toList());
    }
    
    private List<String> extractResponsibilities(String text) {
        // Extract lines that look like responsibilities
        String[] lines = text.split("\\n");
        return Arrays.stream(lines)
            .filter(line -> line.trim().startsWith("•") || 
                          line.trim().startsWith("-") || 
                          line.trim().startsWith("*") ||
                          line.toLowerCase().contains("responsible"))
            .limit(5)
            .collect(Collectors.toList());
    }
    
    private List<String> extractQualifications(String text) {
        // Extract lines that look like qualifications
        String[] lines = text.split("\\n");
        return Arrays.stream(lines)
            .filter(line -> line.toLowerCase().contains("bachelor") ||
                          line.toLowerCase().contains("master") ||
                          line.toLowerCase().contains("experience") ||
                          line.toLowerCase().contains("years"))
            .limit(5)
            .collect(Collectors.toList());
    }
    
    private boolean containsSkill(String text, String skill) {
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(skill) + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
