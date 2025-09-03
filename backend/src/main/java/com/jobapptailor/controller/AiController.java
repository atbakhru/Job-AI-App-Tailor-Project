package com.jobapptailor.controller;

import com.jobapptailor.dto.AiTailorRequest;
import com.jobapptailor.dto.AiTailorResponse;
import com.jobapptailor.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "http://localhost:3000")
public class AiController {
    
    @Autowired
    private AiService aiService;
    
    @PostMapping("/tailor")
    public ResponseEntity<AiTailorResponse> generateTailoredContent(@RequestBody AiTailorRequest request) {
        try {
            AiTailorResponse response = aiService.generateTailoredContent(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
