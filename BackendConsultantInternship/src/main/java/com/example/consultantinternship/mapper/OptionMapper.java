package com.example.consultantinternship.mapper;

import com.example.consultantinternship.dto.OptionDTO;
import com.example.consultantinternship.entity.Option;

import java.util.List;

public abstract class OptionMapper {
    public abstract OptionDTO mapToDto(Option option);
    public abstract List<OptionDTO> mapToDto(List<Option> options);
}
