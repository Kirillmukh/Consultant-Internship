package com.example.consultantinternship.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.NavigableMap;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ResultRisksResponse {
    @JsonProperty("risks_count")
    private NavigableMap<String, Integer> riskCount;
    private List<ResultRisk> data;
    private String summary;
}
