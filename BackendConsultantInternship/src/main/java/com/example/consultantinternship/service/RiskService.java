package com.example.consultantinternship.service;

import com.example.consultantinternship.dto.QuestionDTO;
import com.example.consultantinternship.dto.ResultRisksResponse;
import com.example.consultantinternship.excepiton.WrongIdException;

import java.util.List;
import java.util.Map;

public interface RiskService {
    List<QuestionDTO> getAllQuestions();

    ResultRisksResponse analyzeAnswers(Map<String, String> map) throws WrongIdException;
}
