package com.example.consultantinternship.service;

import com.example.consultantinternship.dto.QuestionDTO;
import com.example.consultantinternship.entity.Question;
import com.example.consultantinternship.mapper.QuestionMapper;
import com.example.consultantinternship.repository.RiskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultRiskService implements RiskService {
    private final RiskRepository riskRepository;
    private final QuestionMapper questionMapper;
    @Override
    public List<QuestionDTO> getQuestions() {
        List<Question> questions =  riskRepository.findAllQuestions();
        return questionMapper.mapToDto(questions);
    }
}
