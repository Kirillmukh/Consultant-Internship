package com.example.consultantinternship.entity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuestionType {
    SINGLE_CHOICE("single-choice"), MULTIPLE_CHOICE("multiple-choice"), TEXT("text"), RATING("rating");
    private final String value;

    @Override
    public String toString() {
        return this.getValue();
    }
}
