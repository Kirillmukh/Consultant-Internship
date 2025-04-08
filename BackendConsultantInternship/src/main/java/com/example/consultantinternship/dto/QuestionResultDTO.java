package com.example.consultantinternship.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class QuestionResultDTO {
    private String id;
    private String title; // todo maybe remove title unnecessary?
    private String text;
}
