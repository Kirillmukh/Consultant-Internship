package com.example.consultantinternship.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class QuestionDTO {
    private String id;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String hint;
    private String text;
    private List<AnswerDTO> answers;
}
