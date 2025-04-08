package com.example.consultantinternship.controller;

import com.example.consultantinternship.controller.payload.ExceptionPayload;
import com.example.consultantinternship.dto.QuestionDTO;
import com.example.consultantinternship.dto.ResultRisksResponse;
import com.example.consultantinternship.excepiton.WrongIdException;
import com.example.consultantinternship.service.RiskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController {

    private final RiskService riskService;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        List<QuestionDTO> responseBody = riskService.getAllQuestions();
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/submit-answers")
    public ResponseEntity<ResultRisksResponse> analyseRisks(@RequestBody Map<String, String> map) throws WrongIdException {
        ResultRisksResponse responseBody = riskService.analyzeAnswers(map);
        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<ExceptionPayload> handleException(WrongIdException e) {
        return ResponseEntity.badRequest().body(new ExceptionPayload("WrongIdException", e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionPayload> handleException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ExceptionPayload("HttpMessageNotReadableException", "Ошибка в теле запроса. Везде должны быть строки"));
    }
}
