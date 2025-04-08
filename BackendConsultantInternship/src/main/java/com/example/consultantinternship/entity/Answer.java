package com.example.consultantinternship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "answers")
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "text")
    private String text;
    @Column(name = "hint")
    private String hint;
    @Column(name = "risk")
    private String risk;
    @Column(name = "explanation")
    private String explanation;
    @Column(name = "note")
    private String note;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_answer_id")
    private List<Answer> subAnswers;
}
