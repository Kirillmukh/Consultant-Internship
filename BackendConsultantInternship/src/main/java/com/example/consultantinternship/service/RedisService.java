package com.example.consultantinternship.service;

import com.example.consultantinternship.dto.AnswerResultDTO;
import com.example.consultantinternship.dto.QuestionResultDTO;
import com.example.consultantinternship.entity.Answer;
import com.example.consultantinternship.entity.Question;
import com.example.consultantinternship.excepiton.WrongIdException;
import com.example.consultantinternship.mapper.AnswerMapper;
import com.example.consultantinternship.mapper.QuestionMapper;
import com.example.consultantinternship.repository.AnswerRepository;
import com.example.consultantinternship.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisService implements CacheService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public void addQuestions(List<Question> questions) {
        if (questions.isEmpty()) {
            return;
        }
        questions.forEach(question -> {
                    redisTemplate.opsForValue().set(question.getId(), questionMapper.mapToRedis(question));
                    this.addAnswers(question.getAnswers());
                }
        );
    }

    @Override
    public void addAnswers(List<Answer> answers) {
        if (answers.isEmpty()) {
            return;
        }
        answers.forEach(answer -> {
            redisTemplate.opsForValue().set(answer.getId(), answerMapper.mapToRedis(answer));
            if (answer.getSubAnswers() == null) {
                return;
            }
            this.addAnswers(answer.getSubAnswers());
        });
    }

    @Override
    public QuestionResultDTO getQuestion(String id) throws WrongIdException {
        Object obj = redisTemplate.opsForValue().get(id);
        if (obj != null) {
            return (QuestionResultDTO) obj;
        }
        Question question = questionRepository.findById(id).orElseThrow(() -> new WrongIdException("Wrong question id where id = " + id));
        QuestionResultDTO questionResultDTO = questionMapper.mapToRedis(question);
        redisTemplate.opsForValue().set(id, questionResultDTO);
        return questionResultDTO;
    }

    @Override
    public AnswerResultDTO getAnswer(String id) throws WrongIdException {
        Object obj = redisTemplate.opsForValue().get(id);
        if (obj != null) {
            return (AnswerResultDTO) obj;
        }
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new WrongIdException("Wrong answer id where id = " + id));
        AnswerResultDTO answerResultDTO = answerMapper.mapToRedis(answer);
        redisTemplate.opsForValue().set(id, answerResultDTO);
        return answerResultDTO;
    }
}
