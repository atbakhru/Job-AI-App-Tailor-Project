package com.jobapptailor.controller;

import com.jobapptailor.dto.ResumeParseRequest;
import com.jobapptailor.dto.ResumeParseResponse;
import com.jobapptailor.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/resume")
@CrossOrigin(origins = "http://localhost:3000")
public class ResumeController {
    
    @Autowired
    private ResumeService resumeService;
    
    @PostMapping("/parse")
    public ResponseEntity<ResumeParseResponse> parseResume(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "text", required = false) String text,
            @RequestParam("userId") Long userId) {
        
        try {
            ResumeParseRequest request = new ResumeParseRequest();
            request.setUserId(userId);
            
            if (file != null && !file.isEmpty()) {
                request.setFile(file);
            } else if (text != null && !text.trim().isEmpty()) {
                request.setText(text);
            } else {
                return ResponseEntity.badRequest().build();
            }
            
            ResumeParseResponse response = resumeService.parseResume(request);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserResumes(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(resumeService.getUserResumes(userId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
