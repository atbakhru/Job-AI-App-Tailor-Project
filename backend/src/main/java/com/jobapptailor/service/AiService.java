package com.jobapptailor.service;

import com.jobapptailor.dto.AiTailorRequest;
import com.jobapptailor.dto.AiTailorResponse;
import com.jobapptailor.model.AiOutput;
import com.jobapptailor.model.Match;
import com.jobapptailor.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AiService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Value("${openai.api.key:}")
    private String openaiApiKey;
    
    @Value("${openai.model:gpt-4}")
    private String openaiModel;
    
    public AiTailorResponse generateTailoredContent(AiTailorRequest request) throws Exception {
        // Find the match
        Optional<Match> matchOpt = matchRepository.findById(request.getMatchId());
        if (matchOpt.isEmpty()) {
            throw new Exception("Match not found");
        }
        
        Match match = matchOpt.get();
        
        // For now, generate mock AI content
        // In a real implementation, this would call OpenAI API
        AiTailorResponse response = generateMockAiContent(match);
        
        // Create and save AiOutput
        AiOutput aiOutput = new AiOutput(match);
        aiOutput.setTailoredBullets(response.getTailoredBullets());
        aiOutput.setCoverLetter(response.getCoverLetter());
        aiOutput.setGaps(response.getGaps());
        aiOutput.setInterviewQuestions(response.getInterviewQuestions());
        aiOutput.setStarPrompts(response.getStarPrompts());
        
        match.setAiOutput(aiOutput);
        matchRepository.save(match);
        
        response.setMessage("AI tailored content generated successfully");
        return response;
    }
    
    private AiTailorResponse generateMockAiContent(Match match) {
        AiTailorResponse response = new AiTailorResponse();
        
        // Mock tailored bullets
        List<String> tailoredBullets = Arrays.asList(
            "• Developed scalable web applications using " + getRandomSkill() + " that improved user experience by 40%",
            "• Led cross-functional team of 5 developers to deliver high-quality software solutions on time",
            "• Implemented CI/CD pipelines reducing deployment time by 60% and improving code quality"
        );
        response.setTailoredBullets(tailoredBullets);
        
        // Mock cover letter
        String coverLetter = String.format(
            "I am excited to apply for the %s position at %s. My experience with %s and proven track record of " +
            "delivering scalable solutions aligns perfectly with your requirements. I have successfully led " +
            "development teams and implemented modern technologies that improved system performance by 40%%. " +
            "I am particularly drawn to %s's mission and would love to contribute to your innovative projects.",
            match.getJobPost().getTitle(),
            match.getJobPost().getCompany(),
            getRandomSkill(),
            match.getJobPost().getCompany()
        );
        response.setCoverLetter(coverLetter);
        
        // Mock gap analysis
        String gaps = "Based on the job requirements, consider strengthening your knowledge in " +
                     "cloud platforms (AWS/Azure) and familiarizing yourself with microservices architecture. " +
                     "These skills are highly valued for this role and can be developed through online courses " +
                     "and personal projects.";
        response.setGaps(gaps);
        
        // Mock interview questions
        List<String> interviewQuestions = Arrays.asList(
            "Describe a challenging technical problem you solved and your approach.",
            "How do you ensure code quality and maintainability in large projects?",
            "Tell me about a time you had to learn a new technology quickly.",
            "How do you handle tight deadlines while maintaining quality?",
            "Describe your experience with agile development methodologies."
        );
        response.setInterviewQuestions(interviewQuestions);
        
        // Mock STAR prompts
        List<String> starPrompts = Arrays.asList(
            "Prepare a STAR story about leading a challenging project with tight deadlines",
            "Think of a situation where you had to resolve conflicts within your development team",
            "Describe a time when you optimized system performance or solved a critical bug"
        );
        response.setStarPrompts(starPrompts);
        
        return response;
    }
    
    private String getRandomSkill() {
        String[] skills = {"React", "Spring Boot", "PostgreSQL", "Docker", "AWS", "TypeScript"};
        return skills[(int) (Math.random() * skills.length)];
    }
}
