package com.jobapptailor.service;

import com.jobapptailor.dto.ResumeParseRequest;
import com.jobapptailor.dto.ResumeParseResponse;
import com.jobapptailor.model.Resume;
import com.jobapptailor.model.User;
import com.jobapptailor.repository.ResumeRepository;
import com.jobapptailor.repository.UserRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ResumeService {
    
    @Autowired
    private ResumeRepository resumeRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    private final Tika tika = new Tika();
    
    public ResumeParseResponse parseResume(ResumeParseRequest request) throws Exception {
        // Find or create default user
        User user = getOrCreateDefaultUser(request.getUserId());
        
        String resumeText;
        String source;
        
        // Extract text from file or use provided text
        if (request.getFile() != null) {
            resumeText = extractTextFromFile(request.getFile());
            source = "file_upload";
        } else if (request.getText() != null && !request.getText().trim().isEmpty()) {
            resumeText = request.getText();
            source = "text_input";
        } else {
            throw new Exception("Either file or text must be provided");
        }
        
        // Extract skills
        List<String> extractedSkills = extractSkills(resumeText);
        
        // Create and save resume
        Resume resume = new Resume(user, source, resumeText);
        resume.setExtractedSkills(extractedSkills);
        resume = resumeRepository.save(resume);
        
        return new ResumeParseResponse(
            resume.getId(),
            resumeText,
            extractedSkills,
            "Resume parsed successfully"
        );
    }
    
    public List<Resume> getUserResumes(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return resumeRepository.findByUserOrderByUpdatedAtDesc(userOpt.get());
    }
    
    private User getOrCreateDefaultUser(Long userId) {
        // First check if user exists
        if (userId != null) {
            Optional<User> userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                return userOpt.get();
            }
        }
        
        // Look for an existing default user by email
        Optional<User> defaultUserOpt = userRepository.findByEmail("user@example.com");
        if (defaultUserOpt.isPresent()) {
            return defaultUserOpt.get();
        }
        
        // Create new default user
        User defaultUser = new User();
        defaultUser.setName("Default User");
        defaultUser.setEmail("user@example.com");
        defaultUser.setCreatedAt(LocalDateTime.now());
        return userRepository.save(defaultUser);
    }
    
    private String extractTextFromFile(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        
        if (contentType != null && contentType.equals("application/pdf")) {
            return extractTextFromPDF(file);
        } else {
            try {
                return tika.parseToString(file.getInputStream());
            } catch (TikaException e) {
                throw new IOException("Failed to parse file: " + e.getMessage(), e);
            }
        }
    }
    
    private String extractTextFromPDF(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            return pdfStripper.getText(document);
        }
    }
    
    private List<String> extractSkills(String text) {
        String[] techSkills = {
            "Java", "Python", "JavaScript", "TypeScript", "React", "Angular", "Vue",
            "Spring Boot", "Node.js", "Express", "Django", "Flask", "PostgreSQL",
            "MySQL", "MongoDB", "Redis", "Docker", "Kubernetes", "AWS", "Azure",
            "Git", "Jenkins", "CI/CD", "REST", "GraphQL", "Microservices", "HTML",
            "CSS", "Sass", "SCSS", "Bootstrap", "Tailwind", "jQuery", "C++", "C#",
            "PHP", "Ruby", "Go", "Rust", "Swift", "Kotlin", "Scala", "R", "MATLAB"
        };
        
        return Arrays.stream(techSkills)
            .filter(skill -> containsSkill(text, skill))
            .collect(Collectors.toList());
    }
    
    private boolean containsSkill(String text, String skill) {
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(skill) + "\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
