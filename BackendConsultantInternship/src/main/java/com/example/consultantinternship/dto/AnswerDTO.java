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
public class AnswerDTO {
    private String id;
    private String text;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String hint;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AnswerDTO> subAnswers;
}
