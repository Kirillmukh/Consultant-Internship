package com.example.consultantinternship.mapper;

import com.example.consultantinternship.dto.QuestionDTO;
import com.example.consultantinternship.entity.Question;

import java.util.List;

//@Mapper(componentModel = "spring", uses = {OptionMapper.class}) // TODO: 28.03.2025 include MapStruct
public abstract class QuestionMapper {
    public abstract QuestionDTO mapToDto(Question question);
    public abstract List<QuestionDTO> mapToDto(List<Question> questions);
}
