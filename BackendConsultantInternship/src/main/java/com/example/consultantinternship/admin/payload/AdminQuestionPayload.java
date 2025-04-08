package com.example.consultantinternship.admin.payload;

import com.example.consultantinternship.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public record AdminQuestionPayload(
        String title,
        String questionHint,
        String questionText,
        List<AdminAnswerPayload> answers
) {
    public Question toQuestion() {
        Question question = new Question();

        question.setTitle(title);
        question.setQuestionHint(questionHint);
        question.setQuestionText(questionText);

        if (answers != null) {
            question.setAnswers(answers.stream()
                    .map(AdminAnswerPayload::toAnswer)
                    .peek(answer -> answer.setQuestion(question))
                    .collect(Collectors.toList()));
        }

        return question;
    }
}