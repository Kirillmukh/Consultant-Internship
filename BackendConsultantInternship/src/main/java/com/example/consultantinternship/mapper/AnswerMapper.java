package com.example.consultantinternship.mapper;

import com.example.consultantinternship.dto.AnswerDTO;
import com.example.consultantinternship.dto.AnswerResultDTO;
import com.example.consultantinternship.entity.Answer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    public abstract AnswerDTO mapToDto(Answer answer);
    public abstract List<AnswerDTO> mapToDto(List<Answer> answers);

    public abstract AnswerResultDTO mapToRedis(Answer answer);
    public abstract List<AnswerResultDTO> mapToRedis(List<Answer> answers);
}
