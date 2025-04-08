package com.example.consultantinternship.repository;

import com.example.consultantinternship.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, String> {
}
