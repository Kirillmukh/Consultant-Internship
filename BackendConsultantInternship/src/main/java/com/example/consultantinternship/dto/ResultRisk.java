package com.example.consultantinternship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultRisk {
    @JsonProperty("question_title")
    private String questionTitle;
    @JsonProperty("question")
    private String question;
    private String answer;
    @JsonProperty("risk_level")
    private String risk;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String explanation;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String note;

    public static ResultRisk from(QuestionResultDTO question, AnswerResultDTO answer) {
        ResultRisk resultRisk = new ResultRisk();

        resultRisk.setQuestionTitle(question.getTitle());
        resultRisk.setQuestion(question.getText());

        resultRisk.setAnswer(answer.getText());
        resultRisk.setRisk(answer.getRisk());
        resultRisk.setExplanation(answer.getExplanation());
        resultRisk.setNote(answer.getNote());

        return resultRisk;
    }
}
