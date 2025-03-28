package com.example.consultantinternship.entity;

import com.example.consultantinternship.entity.util.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Question {
    private Integer id;
    private String text;
    private QuestionType type;
    private String explanation;
    private List<Option> options;
}
