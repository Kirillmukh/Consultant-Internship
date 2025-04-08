package com.example.consultantinternship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AnswerResultDTO  {
    private String id;
    private String text;
    private String risk;
    private String explanation;
    private String note;
}
