package com.example.consultantinternship.dto;

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
    private Integer id;
    private String type;
    private String text;
    private String explanation;
    private List<OptionDTO> options;
}
