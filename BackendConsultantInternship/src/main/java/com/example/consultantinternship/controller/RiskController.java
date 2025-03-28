package com.example.consultantinternship.controller;

import com.example.consultantinternship.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v4")
public class RiskController {
    private final RiskService riskService;
    @GetMapping("/questions")
    public ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(riskService.getQuestions());
    }
    @PostMapping("/submit-answers")
    public ResponseEntity<?> analyseRisks(@RequestBody MultiValueMap<String, String> body) {
        return ResponseEntity.ok().build();
    }
}
