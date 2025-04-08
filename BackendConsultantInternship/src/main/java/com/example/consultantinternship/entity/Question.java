package com.example.consultantinternship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title")
    private String title; // ЦЕНОВАЯ ПОЛИТИКА
    @Column(name = "hint")
    private String questionHint;
    @Column(name = "text")
    private String questionText; // указано ли ... ?
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();
}
