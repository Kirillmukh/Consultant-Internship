package com.example.consultantinternship.admin.payload;

import com.example.consultantinternship.entity.Answer;

import java.util.List;
import java.util.stream.Collectors;

public record AdminAnswerPayload(
        String text,
        String hint,
        String risk,
        String explanation,
        String note,
        List<AdminAnswerPayload> subAnswers
) {
    public Answer toAnswer() {
        Answer answer = new Answer();

        answer.setText(text);
        answer.setHint(hint);
        answer.setRisk(risk);
        answer.setExplanation(explanation);
        answer.setNote(note);

        if (subAnswers != null) {
            answer.setSubAnswers(subAnswers.stream()
                    .map(AdminAnswerPayload::toAnswer)
                    .peek(subAnswer -> subAnswer.setQuestion(answer.getQuestion()))
                    .collect(Collectors.toList()));
        }

        return answer;
    }
}