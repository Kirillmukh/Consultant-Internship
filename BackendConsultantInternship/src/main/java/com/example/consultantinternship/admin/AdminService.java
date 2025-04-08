package com.example.consultantinternship.admin;

import com.example.consultantinternship.entity.Question;
import com.example.consultantinternship.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final QuestionRepository questionRepository;

    @CacheEvict(value = "questions", allEntries = true)
    public void addQuestion(Question question) {
        questionRepository.save(question);
    }
}
