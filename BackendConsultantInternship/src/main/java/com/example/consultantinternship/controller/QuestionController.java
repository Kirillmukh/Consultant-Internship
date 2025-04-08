package com.example.consultantinternship.controller;

import com.example.consultantinternship.controller.payload.ExceptionPayload;
import com.example.consultantinternship.dto.QuestionDTO;
import com.example.consultantinternship.dto.ResultRisksResponse;
import com.example.consultantinternship.excepiton.WrongIdException;
import com.example.consultantinternship.service.RiskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Risks")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController {

    private final RiskService riskService;

    @Operation(
            summary = "Получение всех вопросов для оценки рисков",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        List<QuestionDTO> responseBody = riskService.getAllQuestions();
        return ResponseEntity.ok(responseBody);
    }

    @Operation(
            summary = "Получение результатов по ID ответов пользователя",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Нужны пары в предтавленном виде {questionId: answerId}",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema,
                            examples = {
                                    @ExampleObject(
                                            """
                                                    {
                                                        "2994a7c1-cf45-40bd-ab29-b56cefc096d3": "c3a78055-563f-4c6f-8803-0867de7edf2b",
                                                        "e66cc36d-b3db-472d-8fd5-d0dbdde106cb": "682f416c-d9af-415c-9851-5e88b73a7a22",
                                                        "2c7c6ec6-e212-4422-abbf-a8615f835dc5": "705fc295-1375-42b2-8b0d-29b6253a4619",
                                                        "5f42ff6f-7b50-45e1-bc95-4e837f4b224e": "22ef19cb-2286-4e4a-b239-8ce690be6e47"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            description = """
                                    Выдает два списка data и risk_count. Оба отсортированы по убыванию рисков.
                                    data содержит сущности ответов.
                                    risk_count содержит наименование риска и кол-во ответов с таким риском.
                                    """,
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    """
                                                            {
                                                                 "data": [
                                                                     {
                                                                         "answer": "Да, в пределах одного города",
                                                                         "explanation": "В таком случае Вы (франчайзи) остается ограничен в выборе места осуществления бизнеса, что может создать некоторые проблемы, например, с поиском подходящего помещения.",
                                                                         "note": "ОБРАТИТЕ ВНИМАНИЕ! каждый случай индивидуален, и, если Вы уверены в своем контрагенте или, к примеру, данная франшиза довольно успешна в пределах этого города, то условие может оказаться выигрышным.",
                                                                         "question_title": "УСЛОВИЕ О ВЕДЕНИИ ДЕЯТЕЛЬНОСТИ ИСКЛЮЧИТЕЛЬНО НА ОПРЕДЕЛЕННОЙ ТЕРРИТОРИИ",
                                                                         "question": "Имеется ли в Вашем договоре условие о том, что ведение бизнеса по франшизе должно осуществляться в пределах одной территории (территориального образования)?",
                                                                         "risk_level": "Средний"
                                                                     },
                                                                     {
                                                                         "answer": "Определенный процент от выручки (оборота) (пример формулировки: Роялти составляет X% от валовой выручки (без вычета расходов): Роялти меньше 5%",
                                                                         "explanation": "Несмотря на то, что процент роялти экономически выгоден для франчайзи, появляется риск того, что франчайзер не будет заинтересован в том, чтобы помогать развивать бизнес, так как больших роялти с него он не получает",
                                                                         "note": "ОБРАТИТЕ ВНИМАНИЕ! Каждый случай индивидуален, и этот параметр может оказаться с низким риском, если Вы уверены в своем контрагенте)",
                                                                         "question_title": "РАЗМЕР ВЫПЛАЧИВАЕМОГО ПОЛЬЗОВАТЕЛЕМ ПРАВООБЛАДАТЕЛЮ ЕЖЕМЕСЯЧНОГО ВОЗНАГРАЖДЕНИЯ (РОЯЛТИ)",
                                                                         "question": "Каким образом, согласно Вашему договору, рассчитывается сумма вознаграждения для правообладателя?",
                                                                         "risk_level": "Средний"
                                                                     },
                                                                     {
                                                                         "answer": "Правообладатель не вправе изменять размер, формулу расчёта или порядок уплаты вознаграждения в течение всего срока действия Договора.",
                                                                         "explanation": "При таком условии снижается риск неосновательного увеличения вознаграждения, что в свою очередь делает расходы франчайзи более предсказуемыми",
                                                                         "question_title": "ИЗМЕНЕНИЕ ВОЗНАГРАЖДЕНИЯ ПРАВООБЛАДАТЕЛЮ",
                                                                         "question": "Может ли Правообладатель изменять в одностороннем порядке размер платежей по договору?",
                                                                         "risk_level": "Низкий"
                                                                     },
                                                                     {
                                                                         "answer": "Правообладатель обязан обеспечить регистрацию настоящего договора в установленном порядке",
                                                                         "explanation": "Гражданским Кодексом РФ статьей 1031 по общему правилу обязанность обеспечить регистрацию договора закреплена за Правообладателем.",
                                                                         "question_title": "РЕГИСТРАЦИЯ ДОГОВОРА",
                                                                         "question": "По условиям Вашего договора, на ком лежит обязанность обеспечить регистрацию договора?",
                                                                         "risk_level": "Низкий"
                                                                     }
                                                                 ],
                                                                 "risks_count": {
                                                                     "Средний": 2,
                                                                     "Низкий": 2
                                                                 }
                                                             }
                                                            """
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            description = "Неправильные Id в запросе",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    examples = {
                                            @ExampleObject(
                                                    """
                                                             {
                                                                 "exception": "WrongIdException",
                                                                 "details": "Wrong answer id where id = a4h2-j31l-13h4-hkl3"
                                                             }
                                                            """
                                            )
                                    }

                            )
                    )
            }
    )
    @PostMapping("/submit-answers")
    public ResponseEntity<ResultRisksResponse> analyseRisks(@RequestBody Map<String, String> map) throws WrongIdException {
        ResultRisksResponse responseBody = riskService.analyzeAnswers(map);
        return ResponseEntity.ok(responseBody);
    }

    @ExceptionHandler(WrongIdException.class)
    public ResponseEntity<ExceptionPayload> handleException(WrongIdException e) {
        return ResponseEntity.badRequest().body(new ExceptionPayload("WrongIdException", e.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionPayload> handleException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(new ExceptionPayload("HttpMessageNotReadableException", "Ошибка в теле запроса. Везде должны быть строки"));
    }
}
