package com.example.consultantinternship.service;

import com.example.consultantinternship.dto.AnswerResultDTO;
import com.example.consultantinternship.dto.QuestionResultDTO;
import com.example.consultantinternship.entity.Answer;
import com.example.consultantinternship.entity.Question;
import com.example.consultantinternship.excepiton.WrongIdException;

import java.util.List;

public interface CacheService {
    void addQuestions(List<Question> questions);
    void addAnswers(List<Answer> answers);
    QuestionResultDTO getQuestion(String id) throws WrongIdException;
    AnswerResultDTO getAnswer(String id) throws WrongIdException;
}
