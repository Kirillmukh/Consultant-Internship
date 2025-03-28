package com.example.consultantinternship.repository;

import com.example.consultantinternship.entity.Question;

import java.util.List;

public interface RiskRepository {

    List<Question> findAllQuestions();

}
