package com.example.consultantinternship.repository;

import com.example.consultantinternship.entity.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryRiskRepository implements RiskRepository {

    @Override
    public List<Question> findAllQuestions() {
        return List.of();
    }
}
