package com.example.consultantinternship.service;

import com.example.consultantinternship.dto.*;
import com.example.consultantinternship.entity.Question;
import com.example.consultantinternship.excepiton.WrongIdException;
import com.example.consultantinternship.mapper.QuestionMapper;
import com.example.consultantinternship.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultRiskService implements RiskService {
    private static final Map<String, Integer> riskCosts = Map.of(
            "Высокий", 3,
            "Средний", 2,
            "Низкий", 1
    );
    private static final Comparator<String> riskComparator = (risk1, risk2) -> riskCosts.getOrDefault(risk2, -1) - riskCosts.getOrDefault(risk1, -1);
    private static final Comparator<ResultRisk> resultRiskComparator = (resultRisk1, resultRisk2) -> {
        String risk1 = resultRisk1.getRisk();
        String risk2 = resultRisk2.getRisk();
        return riskComparator.compare(risk1, risk2);
    };

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final CacheService cacheService;

    @Override
    @Cacheable(value = "questions", unless = "#result == null")
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();

        if (questions.isEmpty()) {
            return List.of();
        }

        cacheService.addQuestions(questions);

        return questionMapper.mapToDto(questions);
    }

    @Override
    public ResultRisksResponse analyzeAnswers(Map<String, String> map) throws WrongIdException {
        ResultRisksResponse resultRisksResponse = new ResultRisksResponse();
        NavigableMap<String, Integer> riskCount = new TreeMap<>(riskComparator);
        List<ResultRisk> resultRisks = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            QuestionResultDTO question = cacheService.getQuestion(key);
            AnswerResultDTO answer = cacheService.getAnswer(value);

            if (answer.getRisk() == null) {
                throw new WrongIdException("Wrong answer id where id = " + value);
            }

            riskCount.put(answer.getRisk(), riskCount.getOrDefault(answer.getRisk(), 0) + 1);
            ResultRisk resultRisk = ResultRisk.from(question, answer);

            resultRisks.add(resultRisk);
        }
        resultRisks.sort(resultRiskComparator);

        String summary = this.getSummary(riskCount);

        resultRisksResponse.setRiskCount(riskCount);
        resultRisksResponse.setData(resultRisks);
        resultRisksResponse.setSummary(summary);
        return resultRisksResponse;
    }

    private String getSummary(Map<String, Integer> riskCount) {
        int highCount = riskCount.getOrDefault("Высокий", 0);
        int middleCount = riskCount.getOrDefault("Средний", 0);
        int lowCount = riskCount.getOrDefault("Низкий", 0);

        if (highCount > 2) {
            return "В Вашем договоре было выявлено большое количество условий с высоким риском, что может повлечь неблагоприятные для Вас последствия. Рекомендуем обратить особе внимание на эти условия.";
        }
        if (highCount != 0) {
            return "В Вашем договоре были выявлены условия с высоким риском, что может повлечь неблагоприятные для Вас последствия. Рекомендуем обратить особе внимание на эти условия.";
        }
        if (middleCount > (lowCount + highCount) / 2) {
            return "В Вашем договоре не выявлено условий с высоким риском, однако ряд условий влечет возможность возникновения среднего уровня риска. Рекомендуем обратить особе внимание на эти условия.";
        }

        return "В вашем договоре не выявлены условия, которые могут повлечь неблагоприятный для Вас последствия. Рекомендуем ознакомиться с пояснениями по каждому вопросу";
    }
}
