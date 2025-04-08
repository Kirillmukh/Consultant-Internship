package com.example.consultantinternship.mapper;

import com.example.consultantinternship.dto.QuestionDTO;
import com.example.consultantinternship.dto.QuestionResultDTO;
import com.example.consultantinternship.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})
public abstract class QuestionMapper {
    @Mapping(target = "text", source = "questionText")
    @Mapping(target = "hint", source = "questionHint")
    public abstract QuestionDTO mapToDto(Question question);
    public abstract List<QuestionDTO> mapToDto(List<Question> questions);

    @Mapping(target = "text", source = "questionText")
    public abstract QuestionResultDTO mapToRedis(Question question);
    public abstract List<QuestionResultDTO> mapToRedis(List<Question> questions);
}
