package com.example.consultantinternship.config;

import com.example.consultantinternship.entity.Answer;
import com.example.consultantinternship.entity.Question;
import com.example.consultantinternship.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class MigrationData {
//    @Bean
    public CommandLineRunner migrate(QuestionRepository questionRepository) {
        return args -> {
            // Категория 1: Условие о ведении деятельности исключительно на определенной территории
            Question territoryQuestion = new Question();
            territoryQuestion.setTitle("УСЛОВИЕ О ВЕДЕНИИ ДЕЯТЕЛЬНОСТИ ИСКЛЮЧИТЕЛЬНО НА ОПРЕДЕЛЕННОЙ ТЕРРИТОРИИ");
            territoryQuestion.setQuestionText("Имеется ли в Вашем договоре условие о том, что ведение бизнеса по франшизе должно осуществляться в пределах одной территории (территориального образования)?");

            List<Answer> territoryAnswers = new ArrayList<>();

            Answer territoryAnswerYes = new Answer();
            territoryAnswerYes.setText("Да");
            territoryAnswerYes.setQuestion(territoryQuestion);

            List<Answer> territoryAnswerYesSubs = new ArrayList<>();

            Answer territoryAnswerYes1 = new Answer();
            territoryAnswerYes1.setText("В пределах одного района или улицы");
            territoryAnswerYes1.setRisk("Высокий");
            territoryAnswerYes1.setExplanation("В таком случае Вы (франчайзи) сильно ограничены в выборе места осуществления бизнеса. Выбор удачного места расположения кофейни является ключевым фактором в успехе такой франшизы, поэтому такое условие будет невыгодным для Вас (франчайзи)");

            Answer territoryAnswerYes2 = new Answer();
            territoryAnswerYes2.setText("В пределах одного города");
            territoryAnswerYes2.setRisk("Средний");
            territoryAnswerYes2.setExplanation("В таком случае Вы (франчайзи) остается ограничен в выборе места осуществления бизнеса, что может создать некоторые проблемы, например, с поиском подходящего помещения.");
            territoryAnswerYes2.setNote("Каждый случай индивидуален, и, если Вы уверены в своем контрагенте или, к примеру, данная франшиза довольно успешна в пределах этого города, то условие может оказаться выигрышным.");

            Answer territoryAnswerYes3 = new Answer();
            territoryAnswerYes3.setText("В пределах одного субъекта федерации");
            territoryAnswerYes3.setRisk("Средний");
            territoryAnswerYes3.setExplanation("В таком случае Вы (франчайзи) остаетесь довольно ограничены в выборе места осуществления бизнеса.");
            territoryAnswerYes3.setNote("Каждый случай индивидуален, и, если Вы уверены в своем контрагенте или, к примеру, данная франшиза довольно успешна в пределах этого субъекта федерации, то условие может оказаться выигрышным.");

            Answer territoryAnswerYes4 = new Answer();
            territoryAnswerYes4.setText("В пределах страны");
            territoryAnswerYes4.setRisk("Низкий");
            territoryAnswerYes4.setExplanation("В таком случае, Вам (франчайзи) доступен широкий выбор места ведения бизнеса без привязки в какой-либо территории, кроме страны.");
            territoryAnswerYes4.setNote("Каждый случай индивидуален, и за Вами, в таком случае, остается выбор удачного места расположения кофейни, что не лишает Вас риска не преуспеть в бизнесе.");

            territoryAnswerYesSubs.add(territoryAnswerYes1);
            territoryAnswerYesSubs.add(territoryAnswerYes2);
            territoryAnswerYesSubs.add(territoryAnswerYes3);
            territoryAnswerYesSubs.add(territoryAnswerYes4);
            territoryAnswerYes.setSubAnswers(territoryAnswerYesSubs);

            Answer territoryAnswerNo = new Answer();
            territoryAnswerNo.setText("Нет");
            territoryAnswerNo.setRisk("Низкий");
            territoryAnswerNo.setExplanation("Такое условие является наиболее оптимальным вариантом, так как Вам (франчайзи) доступен широкий выбор места ведения бизнеса без привязки в какой-либо территории.");
            territoryAnswerNo.setQuestion(territoryQuestion);

            territoryAnswers.add(territoryAnswerYes);
            territoryAnswers.add(territoryAnswerNo);

            territoryQuestion.setAnswers(territoryAnswers);
            questionRepository.save(territoryQuestion);

            // Категория 2: Размер выплачиваемого пользователем правообладателю ежемесячного вознаграждения (роялти)
            Question royaltyQuestion = new Question();
            royaltyQuestion.setTitle("РАЗМЕР ВЫПЛАЧИВАЕМОГО ПОЛЬЗОВАТЕЛЕМ ПРАВООБЛАДАТЕЛЮ ЕЖЕМЕСЯЧНОГО ВОЗНАГРАЖДЕНИЯ (РОЯЛТИ)");
            royaltyQuestion.setQuestionText("Каким образом, согласно Вашему договору, рассчитывается сумма вознаграждения для правообладателя?");
            royaltyQuestion.setQuestionHint("пример формулировки: Роялти составляет X% от валовой выручки (без вычета расходов)");

            List<Answer> royaltyAnswers = new ArrayList<>();

            // Ответ 2.1
            Answer royaltyAnswer1 = new Answer();
            royaltyAnswer1.setText("Роялти меньше 5%");
            royaltyAnswer1.setRisk("Средний");
            royaltyAnswer1.setExplanation("Несмотря на то, что процент роялти экономически выгоден для франчайзи, появляется риск того, что франчайзер не будет заинтересован в том, чтобы помогать развивать бизнес, так как больших роялти с него он не получает.");
            royaltyAnswer1.setNote("Каждый случай индивидуален, и этот параметр может оказаться с низким риском, если Вы уверены в своем контрагенте.");
            royaltyAnswer1.setQuestion(royaltyQuestion);

            // Ответ 2.2
            Answer royaltyAnswer2 = new Answer();
            royaltyAnswer2.setText("Роялти в диапазоне от 5 до 10%");
            royaltyAnswer2.setRisk("Низкий");
            royaltyAnswer2.setExplanation("Анализ рынка франшиз кофеен показал, что в среднем самый экономически выгодный для обеих сторон процент роялти составляет от 5 до 10%.");
            royaltyAnswer2.setQuestion(royaltyQuestion);

            // Ответ 2.3
            Answer royaltyAnswer3 = new Answer();
            royaltyAnswer3.setText("Роялти больше 10-20%");
            royaltyAnswer3.setRisk("Высокий");
            royaltyAnswer3.setExplanation("Слишком большой процент ежемесячных роялти тормозит развитие бизнеса и увеличивает срок окупаемости франшизы.");
            royaltyAnswer3.setNote("Помимо уплаты роялти, Вам, как франчайзи, придется нести другие расходы для ведения бизнеса, поэтому роялти не должны превышать 1/10 прибыли Вашего бизнеса.");
            royaltyAnswer3.setQuestion(royaltyQuestion);

            // Ответ 2.4
            Answer royaltyAnswer4 = new Answer();
            royaltyAnswer4.setText("Фиксированная ставка");
            royaltyAnswer4.setRisk("Средний");
            royaltyAnswer4.setExplanation("Преимуществами для франчайзи являются предсказуемость расходов (легче планировать бюджет) и отсутствие зависимости от прибыльности бизнеса. Неблагоприятными моментами являются высокий в начале ведения бизнеса риск– если бизнес не вышел на окупаемость, фиксированные платежи могут быть тяжелыми.");
            royaltyAnswer4.setNote("Это условие может иметь низкий при стабильном доходе бизнеса – когда бизнес работает устойчиво (не для новичков в франчайзинге).");
            royaltyAnswer4.setHint("пример формулировки - Роялти по настоящему договору определяются фиксированной суммой (например, 50 000 руб./мес.), независимо от выручки.");
            royaltyAnswer4.setQuestion(royaltyQuestion);

            // Ответ 2.5
            Answer royaltyAnswer5 = new Answer();
            royaltyAnswer5.setText("Смешанные");
            royaltyAnswer5.setRisk("Средний");
            royaltyAnswer5.setExplanation("Преимуществом для франчайзи является гарантированный минимум для франчайзера, но гибкость при росте продаж. Неблагоприятным моментом можно назвать невыгодность такого условия при низкой маржинальности бизнеса. Такое условие сочетает риски фиксированных и процентных платежей.");
            royaltyAnswer5.setQuestion(royaltyQuestion);

            // Ответ 2.6
            Answer royaltyAnswer6 = new Answer();
            royaltyAnswer6.setText("Роялти через обязательные закупки");
            royaltyAnswer6.setRisk("Высокий");
            royaltyAnswer6.setExplanation("Преимуществом для франчайзи является отсутствие отдельного регулярного платежа – такое условие называют также \"скрытой\" формой роялти. Минусом является зависимость франчайзи от франчайзера в поставках.");
            royaltyAnswer6.setNote("Каждый случай индивидуален, и необходимо дополнительно согласовывать данное условие с контрагентом, так как, если закупочные цены завышены, снижается рентабельность бизнеса и выгода франчайзи.");
            royaltyAnswer6.setHint("Пользователь обязан закупать товары/услуги у франчайзера, а роялти включена в их стоимость.");
            royaltyAnswer6.setQuestion(royaltyQuestion);

            // Ответ 2.7
            Answer royaltyAnswer7 = new Answer();
            royaltyAnswer7.setText("Отсутствие роялти (только паушальный взнос)");
            royaltyAnswer7.setRisk("Низкий");
            royaltyAnswer7.setExplanation("Преимуществом для франчайзи является отсутствие постоянной финансовой нагрузки. Минусом является то, что франчайзер может меньше поддерживать бизнес, так как не имеет в нем финансовой выгоды.");
            royaltyAnswer7.setNote("Это условие может иметь низкий риск при стабильном доходе бизнеса - когда бизнес работает устойчиво (не для новичков в франчайзинге).");
            royaltyAnswer7.setHint("Пользователь платит только первоначальный взнос, а далее – без регулярных отчислений.");
            royaltyAnswer7.setQuestion(royaltyQuestion);


            royaltyAnswers.add(royaltyAnswer1);
            royaltyAnswers.add(royaltyAnswer2);
            royaltyAnswers.add(royaltyAnswer3);
            royaltyAnswers.add(royaltyAnswer4);
            royaltyAnswers.add(royaltyAnswer5);
            royaltyAnswers.add(royaltyAnswer6);
            royaltyAnswers.add(royaltyAnswer7);

            royaltyQuestion.setAnswers(royaltyAnswers);
            questionRepository.save(royaltyQuestion);

            // Категория 3: Изменение вознаграждения правообладателю
            Question paymentChangeQuestion = new Question();
            paymentChangeQuestion.setTitle("ИЗМЕНЕНИЕ ВОЗНАГРАЖДЕНИЯ ПРАВООБЛАДАТЕЛЮ");
            paymentChangeQuestion.setQuestionText("Может ли Правообладатель изменять в одностороннем порядке размер платежей по договору?");

            List<Answer> paymentChangeAnswers = new ArrayList<>();

            // Ответ 3.1
            Answer paymentChangeAnswer1 = new Answer();
            paymentChangeAnswer1.setText("Правообладатель вправе изменять размер периодических платежей в течение срока действия настоящего Договора, и не ограничена частота изменений.");
            paymentChangeAnswer1.setRisk("Высокий");
            paymentChangeAnswer1.setExplanation("Франчайзер может повысить платежи даже в период кризиса или падения спроса, не учитывая проблемы франчайзи. Кроме того, могут возникнуть проблемы с планированием бюджета из-за неожиданного повышения размера вознаграждения.");
            paymentChangeAnswer1.setNote("Рекомендуем Вам согласовать это условие так, чтобы Правообладатель мог повышать размер платежей не чаще чем 1 раз в год (можно установить и иную периодичность изменения вознаграждения). Также рекомендуем прописать условие об обязательности уведомления Вас Правообладателем за определенный срок, в случае если он планирует повысить платежи. Также советуем ограничить возможность повышения платежей определенным процентом (например, не более чем на 10%).");
            paymentChangeAnswer1.setQuestion(paymentChangeQuestion);

            // Ответ 3.2
            Answer paymentChangeAnswer2 = new Answer();
            paymentChangeAnswer2.setText("Правообладатель вправе изменять размер периодических платежей в течение срока действия настоящего Договора, но частота изменений ограничена (например, один раз в год).");
            paymentChangeAnswer2.setRisk("Средний");
            paymentChangeAnswer2.setExplanation("Правообладатель может повысить размер платежей, но не может делать этого произвольно.");
            paymentChangeAnswer2.setNote("Рекомендуем Вам  прописать условие об обязательности уведомления Вас Правообладателем за определенный срок, в случае если он планирует повысить платежи. Также советуем ограничить возможность повышения платежей определенным процентом (например, не более чем на 10%).");
            paymentChangeAnswer2.setQuestion(paymentChangeQuestion);

            // Ответ 3.3
            Answer paymentChangeAnswer3 = new Answer();
            paymentChangeAnswer3.setText("Правообладатель не вправе изменять размер, формулу расчёта или порядок уплаты вознаграждения в течение всего срока действия Договора.");
            paymentChangeAnswer3.setRisk("Низкий");
            paymentChangeAnswer3.setExplanation("При таком условии снижается риск неосновательного увеличения вознаграждения, что в свою очередь делает расходы франчайзи более предсказуемыми.");
            paymentChangeAnswer3.setQuestion(paymentChangeQuestion);

            paymentChangeAnswers.add(paymentChangeAnswer1);
            paymentChangeAnswers.add(paymentChangeAnswer2);
            paymentChangeAnswers.add(paymentChangeAnswer3);

            paymentChangeQuestion.setAnswers(paymentChangeAnswers);
            questionRepository.save(paymentChangeQuestion);

            // Категория 4: Регистрация договора
            Question registrationQuestion = new Question();
            registrationQuestion.setTitle("РЕГИСТРАЦИЯ ДОГОВОРА");
            registrationQuestion.setQuestionText("По условиям Вашего договора, на ком лежит обязанность обеспечить регистрацию договора?");
            registrationQuestion.setQuestionHint("Обычно это условие содержится в пункте «Регистрация договора» или «Права и обязанности сторон»");

            List<Answer> registrationAnswers = new ArrayList<>();

            // Ответ 4.1
            Answer registrationAnswer1 = new Answer();
            registrationAnswer1.setText("Правообладатель обязан обеспечить регистрацию настоящего договора в установленном порядке");
            registrationAnswer1.setRisk("Низкий");
            registrationAnswer1.setExplanation("""
                    Согласно <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a>, по общему правилу обязанность обеспечить регистрацию договора закреплена за Правообладателем.
                    """);
            registrationAnswer1.setQuestion(registrationQuestion);

            // Ответ 4.2
            Answer registrationAnswer2 = new Answer();
            registrationAnswer2.setText("Все расходы, связанные с регистрацией настоящего Договора, несет Пользователь.");
            registrationAnswer2.setRisk("Высокий");
            registrationAnswer2.setExplanation("""
                    Согласно <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a>, по общему правилу обязанность обеспечить регистрацию договора закреплена за Правообладателем. Регистрация договора за Ваш счет влечет для Вас возникновение дополнительных финансовых издержек.
                    """);
            registrationAnswer2.setNote("Рекомендуем Вам согласовать с Правообладателем, это условие так, чтобы обязанность обеспечить регистрацию договора возлагалась на Правообладателя. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.");
            registrationAnswer2.setQuestion(registrationQuestion);

            registrationAnswers.add(registrationAnswer1);
            registrationAnswers.add(registrationAnswer2);
            registrationQuestion.setAnswers(registrationAnswers);
            questionRepository.save(registrationQuestion);

            // Категория 5: Лицензии
            Question licensesQuestion = new Question();
            licensesQuestion.setTitle("ЛИЦЕНЗИИ");
            licensesQuestion.setQuestionText("Указано ли в Вашем договоре на обязанность Правообладателя обеспечить оформление лицензий в установленном порядке?");

            List<Answer> licensesAnswers = new ArrayList<>();

            // Ответ 5.1
            Answer licensesAnswer1 = new Answer();
            licensesAnswer1.setText("Прописана обязанность Правообладателя обеспечить оформление лицензий в установленном порядке");
            licensesAnswer1.setRisk("Низкий");
            licensesAnswer1.setExplanation("""
                    Согласно <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a>, по общему правилу такая обязанность возлагается на Правообладателя.
                    """);
            licensesAnswer1.setQuestion(licensesQuestion);

            // Ответ 5.2
            Answer licensesAnswer2 = new Answer();
            licensesAnswer2.setText("Такое условие прописано, но указано, что расходы по оформлению лицензий возлагаются на Пользователя");
            licensesAnswer2.setRisk("Средний");
            licensesAnswer2.setExplanation("""
                    Согласно <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a>, такая обязанность возлагается на Правообладателя. Необходимость оформления лицензий влечет для Вас возникновение дополнительных финансовых и временных издержек.
                    """);
            licensesAnswer2.setNote("Рекомендуем Вам согласовать с Правообладателем, это условие так, чтобы обязанность обеспечить оформление лицензий возлагалась на Правообладателя. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.");
            licensesAnswer2.setQuestion(licensesQuestion);

            // Ответ 5.3
            Answer licensesAnswer3 = new Answer();
            licensesAnswer3.setText("Такое условие не прописано");
            licensesAnswer3.setRisk("Высокий");
            licensesAnswer3.setExplanation("""
                    В <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a> закреплена обязанность Правообладателя обеспечить проведение консультаций. Отсутствие надлежащим образом оформленных лицензий влечет риск привлечения Вас к юридической ответственности.
                    """);
            licensesAnswer3.setNote("Рекомендуем Вам согласовать с Правообладателем, это условие так, чтобы обязанность обеспечить оформление лицензий возлагалась на Правообладателя. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.");
            licensesAnswer3.setQuestion(licensesQuestion);

            licensesAnswers.add(licensesAnswer1);
            licensesAnswers.add(licensesAnswer2);
            licensesAnswers.add(licensesAnswer3);
            licensesQuestion.setAnswers(licensesAnswers);
            questionRepository.save(licensesQuestion);

            // Категория 6: Ценовая политика
            Question pricingQuestion = new Question();
            pricingQuestion.setTitle("ЦЕНОВАЯ ПОЛИТИКА");
            pricingQuestion.setQuestionText("Как в Вашем договоре сформулирована обязанность согласовывать с правообладателем (франчайзером) ценовую политику ведения бизнеса?");

            List<Answer> pricingAnswers = new ArrayList<>();

            // Ответ 6.1
            Answer pricingAnswer1 = new Answer();
            pricingAnswer1.setText("Правообладатель дает указания, готовит и координирует мероприятия по стимулированию сбыта Товара в соответствии с действующей у Правообладателя системой скидок и дисконтировании. Правообладатель устанавливает ценовую политику в отношении Товаров. Пользователь следует единой системе ценообразования, применяет систему льгот, скидок и дисконтов, а также участвует в иных дисконтных акциях, организованных и применяемых Правообладателем");
            pricingAnswer1.setRisk("Высокий");
            pricingAnswer1.setExplanation("Вы не сможете самостоятельно устанавливать цены и должны будете действовать в соответствии с указаниями правообладателя (франчайзера). Существует вероятность того, что Вам придется работать в убыток либо выходить в “ноль” без получения прибыли.");
            pricingAnswer1.setQuestion(pricingQuestion);

            // Ответ 6.2
            Answer pricingAnswer2 = new Answer();
            pricingAnswer2.setText("Правообладатель не имеет права определять отпускные цены на продукцию (услуги), реализуемую (оказываемые) Пользователем своим клиентам, а также устанавливать верхний или нижний пределы этих цен. Указания об уровне отпускных цен на продукцию могут даваться Франчайзером только в качестве рекомендаций.");
            pricingAnswer2.setRisk("Низкий");
            pricingAnswer2.setExplanation("Вы не ограничены в ценообразовании и можете сами выставлять и корректировать цены по своему усмотрению, что будет явным преимуществом для ведения бизнеса.");
            pricingAnswer2.setQuestion(pricingQuestion);
            pricingAnswer2.setHint("Пользователь имеет право: самостоятельно устанавливать и утверждать розничные цены на Продукцию и Дополнительную продукцию;\n" +
                    "Пользователь вправе самостоятельно разрабатывать и проводить собственные акции и спецпредложения. Указанные мероприятия (их содержание и виды применяемых в процессе их проведения информационных и (или) рекламных материалов) должны быть письменно согласованы с Правообладателем в срок не позднее N дней до момента их введения в действие");

            pricingAnswers.add(pricingAnswer1);
            pricingAnswers.add(pricingAnswer2);
            pricingQuestion.setAnswers(pricingAnswers);
            questionRepository.save(pricingQuestion);

            // Категория 7: Условие о поставках сырья
            Question suppliesQuestion = new Question();
            suppliesQuestion.setTitle("УСЛОВИЕ О ПОСТАВКАХ СЫРЬЯ");
            suppliesQuestion.setQuestionText("Указано ли в Вашем договоре на обязанность пользователя закупать продукцию только у Правообладателя или у определенных поставщиков?");

            List<Answer> suppliesAnswers = new ArrayList<>();

            // Ответ 7.1
            Answer suppliesAnswer1 = new Answer();
            suppliesAnswer1.setText("Пользователь обязан приобретать продукцию только у поставщиков, рекомендованных Правообладателем");
            suppliesAnswer1.setRisk("Высокий");
            suppliesAnswer1.setExplanation("Данное условие создает определенные рамки для франчайзи. Ограничение выбора лишает франчайзи возможности влиять на качество товаров. Низкое качество со стороны поставщика может навредить репутации бизнеса.Франчайзер или назначенные поставщики могут устанавливать завышенные цены, лишая франчайзи возможности искать более выгодные варианты. Кроме того, становится практически невозможным реагировать на изменения рынка, например, использовать локальных поставщиков для снижения логистических издержек");
            suppliesAnswer1.setQuestion(suppliesQuestion);

            // Ответ 7.2
            Answer suppliesAnswer2 = new Answer();
            suppliesAnswer2.setText("Пользователь вправе закупать товар у любых поставщиков по своему выбору, при условии соблюдении требований Договора и Стандартов качества");
            suppliesAnswer2.setRisk("Низкий");
            suppliesAnswer2.setExplanation("Вы не ограничены никакими рамками в выборе поставщика оборудования и сырья и сможете приобретать их по выгодному соотношению цены и качества, действуя на свое усмотрение.");
            suppliesAnswer2.setQuestion(suppliesQuestion);

            suppliesAnswers.add(suppliesAnswer1);
            suppliesAnswers.add(suppliesAnswer2);
            suppliesQuestion.setAnswers(suppliesAnswers);
            questionRepository.save(suppliesQuestion);

            // Категория 8: Условия о поставке оборудования
            Question equipmentQuestion = new Question();
            equipmentQuestion.setTitle("УСЛОВИЯ О ПОСТАВКЕ ОБОРУДОВАНИЯ");
            equipmentQuestion.setQuestionText("Содержится ли в Вашем договоре обязанность пользователя закупать необходимое оборудование только у Правообладателя или у определенных поставщиков?");

            List<Answer> equipmentAnswers = new ArrayList<>();

            // Ответ 8.1
            Answer equipmentAnswer1 = new Answer();
            equipmentAnswer1.setText("Пользователь обязан приобретать необходимое оборудование только у Правообладателя или рекомендованных поставщиков");
            equipmentAnswer1.setRisk("Высокий");
            equipmentAnswer1.setExplanation("Данное условие создает определенные рамки для франчайзи. Франчайзер может устанавливать завышенные цены на оборудование, так как франчайзи не имеет альтернатив. Кроме того, это оборудование может не соответствовать современным технологиям и потребностям франчайзи, а также плохо поддаваться ремонту.");
            equipmentAnswer1.setQuestion(equipmentQuestion);

            // Ответ 8.2
            Answer equipmentAnswer2 = new Answer();
            equipmentAnswer2.setText("Франчайзи вправе закупать оборудование у любых поставщиков по своему выбору, при условии соблюдении требований Договора и Правил безопасности");
            equipmentAnswer2.setRisk("Низкий");
            equipmentAnswer2.setExplanation("Вы не ограничены никакими рамками в выборе поставщика оборудования и сырья и сможете приобретать их по выгодному соотношению цены и качества, действуя на свое усмотрение");
            equipmentAnswer2.setQuestion(equipmentQuestion);

            equipmentAnswers.add(equipmentAnswer1);
            equipmentAnswers.add(equipmentAnswer2);
            equipmentQuestion.setAnswers(equipmentAnswers);
            questionRepository.save(equipmentQuestion);

            // Категория 9: Консультации
            Question consultationsQuestion = new Question();
            consultationsQuestion.setTitle("КОНСУЛЬТАЦИИ");
            consultationsQuestion.setQuestionText("Указано ли в Вашем договоре обязанность Правообладателя консультировать Пользователя по вопросам деятельности франшизы?");
            consultationsQuestion.setQuestionHint("Примеры направлений консультирования:\n" +
                    "- по выбору Помещений (месторасположение и технические характеристики, определяющие возможность размещения Предприятия в помещении), и консультирование Пользователя по оптимальному выбору месторасположения и пригодности выбранного Пользователем Помещения для размещения Предприятия;\n" +
                    "- по планированию распределения площадей и расположению оборудования;\n" +
                    "- по управлению и функционированию Предприятия;\n" +
                    "- по эффективному использованию и техническому обслуживанию используемого на Предприятии оборудования;\n" +
                    "- по обучению персонала Предприятия;\n" +
                    "- по оформлению (дизайну) Предприятия, дизайну и расстановке мебели и декоративным элементам;\n" +
                    "- по вопросам финансов, управления, кадров и кадровой политики, администрирования и продвижения Продукции, направленное на ведение деятельности Предприятия наиболее эффективным способом;\n" +
                    "- по вопросам лицензирования деятельности Предприятия и сертификации Продукции;\n" +
                    "- по дополнительно возникающим вопросам, имеющим непосредственное отношение к Системе и т.д.\n");

            List<Answer> consultationsAnswers = new ArrayList<>();

            // Ответ 9.1
            Answer consultationsAnswer1 = new Answer();
            consultationsAnswer1.setText("Прописана обязанность Правообладателя консультировать Пользователя по широкому кругу вопросов");
            consultationsAnswer1.setRisk("Низкий");
            consultationsAnswer1.setExplanation("""
                            Гражданским Кодексом в <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">ст.1031 РФ</a> закреплена обязанность Правообладателя обеспечить проведение консультаций. Отсутствие надлежащим образом оформленных лицензий влечет риск привлечения Вас к юридической ответственности.  закреплена обязанность Правообладателя обеспечить проведение консультаций. Если прописан широкий круг направлений консультирования, Правообладатель не сможет отказаться от дачи советов по указанным вопросам.
                            """);
            consultationsAnswer1.setQuestion(consultationsQuestion);

            // Ответ 9.2
            Answer consultationsAnswer2 = new Answer();
            consultationsAnswer2.setText("Прописана обязанность Правообладателя консультировать Пользователя, но не указано, по каким именно вопросам");
            consultationsAnswer2.setRisk("Средний");
            consultationsAnswer2.setExplanation("""
                            В <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a> закреплена обязанность Правообладателя обеспечить проведение консультаций. Отсутствие надлежащим образом оформленных лицензий влечет риск привлечения Вас к юридической ответственности. закреплена обязанность Правообладателя обеспечить проведение консультаций. Если в договоре не указаны конкретные направления консультаций, между Вами и Правообладателем могут возникнуть недопонимания и разногласия.
                            """);
            consultationsAnswer2.setNote("Рекомендуем Вам дополнительно согласовать эти вопросы с Правообладателем. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.");
            consultationsAnswer2.setQuestion(consultationsQuestion);

            // Ответ 9.3
            Answer consultationsAnswer3 = new Answer();
            consultationsAnswer3.setText("Не прописана обязанность Правообладателя консультировать Пользователя");
            consultationsAnswer3.setRisk("Высокий");
            consultationsAnswer3.setExplanation("""
                    В <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102454&cacheid=07034A6902A2A137393E74C940771080&mode=splus&rnd=5Btq4g#L0cyshUe8hvMPUaM" target="_blank">статье 1031 Гражданского кодекса РФ</a> закреплена обязанность Правообладателя обеспечить проведение консультаций. Отсутствие надлежащим образом оформленных лицензий влечет риск привлечения Вас к юридической ответственности.  закреплена обязанность Правообладателя обеспечить проведение консультаций. Если в договоре не указано на такую обязанность, у Вас могут возникнуть организационные, финансовые и иные трудности при ведении бизнеса.
                    """);
            consultationsAnswer3.setNote("Рекомендуем Вам дополнительно согласовать эти вопросы с Правообладателем. Если не представляется возможным внести соответствующее изменение в договор, советуем провести переговоры по заключению дополнительного соглашения. Если же в результате переговоров Вам не удалось достичь заключения дополнительного соглашения, советуем Вам составить протокол разногласий.");
            consultationsAnswer3.setQuestion(consultationsQuestion);

            consultationsAnswers.add(consultationsAnswer1);
            consultationsAnswers.add(consultationsAnswer2);
            consultationsAnswers.add(consultationsAnswer3);
            consultationsQuestion.setAnswers(consultationsAnswers);
            questionRepository.save(consultationsQuestion);

            // Категория 10: ПРОВЕРКА ПРАВООБЛАДАТЕЛЕМ ДЕЯТЕЛЬНОСТИ ПОЛЬЗОВАТЕЛЯ
            Question inspectionQuestion = new Question();
            inspectionQuestion.setTitle("ПРОВЕРКА ПРАВООБЛАДАТЕЛЕМ ДЕЯТЕЛЬНОСТИ ПОЛЬЗОВАТЕЛЯ");
            inspectionQuestion.setQuestionText("Указан ли в Вашем договоре порядок проведения Правообладателем проверок деятельности Пользователя?");
            inspectionQuestion.setQuestionHint("Обычно это условие содержится в пункте “Контроль за соблюдением качества работы предприятия”, “Проверка деятельности предприятия” или “Права и обязанности сторон”");

            List<Answer> inspectionAnswers = new ArrayList<>();

            // Ответ 10.1
            Answer inspectionAnswer1 = new Answer();
            inspectionAnswer1.setText("Прописана обязанность Пользователя в любое время по усмотрению Правообладателя предоставлять последнему возможность осуществлять проверку любых аспектов деятельности Пользователя.");
            inspectionAnswer1.setRisk("Высокий");
            inspectionAnswer1.setExplanation("Если Правообладатель имеет право провести проверку деятельности Вашего предприятия в любое время, по любым направлениям деятельности и в любом объеме, вы не сможете отказаться от проведения проверки и будете обязаны допустить Правообладателя на территорию Вашего предприятия, предоставить ему все запрашиваемые документы и т.д. Это влечет риск приостановки деятельности Вашего предприятия на время проведения незапланированной проверки, в результате чего Вы можете потерять прибыль.");
            inspectionAnswer1.setNote("Рекомендуем Вам согласовать с Правообладателем такое условие договора, по которому Правообладатель может проводить проверки в обычные часы работы Пользователя, и такие проверки не мешают нормальному течению хозяйственной деятельности Пользователя. Также рекомендуем Вам согласовать возможные направления проверок и формы проверок. Также рекомендуем Вам согласовать обязанность Правообладателя уведомлять о намерении провести проверку.");
            inspectionAnswer1.setQuestion(inspectionQuestion);

            // Ответ 10.2
            Answer inspectionAnswer2 = new Answer();
            inspectionAnswer2.setText("Прописана обязанность Пользователя позволять Правообладателю осуществлять проверку деятельности Пользователя при условии, что такие проверки происходят в обычные часы работы Пользователя, не мешают нормальному течению его хозяйственной деятельности.");
            inspectionAnswer2.setRisk("Низкий");
            inspectionAnswer2.setExplanation("Правообладатель не имеет право провести проверку деятельности Вашего предприятия в любое время, по любым направлениям деятельности, что могло бы повлечь риск приостановки деятельности Вашего предприятия на время проведения незапланированной проверки\n");
            inspectionAnswer2.setQuestion(inspectionQuestion);
            inspectionAnswer2.setNote(inspectionAnswer1.getNote());

            // Ответ 10.3
            Answer inspectionAnswer3 = new Answer();
            inspectionAnswer3.setText("Такое условие не прописано.");
            inspectionAnswer3.setRisk("Средний");
            inspectionAnswer3.setExplanation("Отсутствие регламентации порядка проведения проверок Вашей деятельности влечет риск возникновения разногласий.");
            inspectionAnswer3.setQuestion(inspectionQuestion);
            inspectionAnswer3.setNote("Рекомендуем Вам согласовать возможные направления проверок и формы проверок. Также рекомендуем Вам согласовать обязанность Правообладателя уведомлять о намерении провести проверку.");

            inspectionAnswers.add(inspectionAnswer1);
            inspectionAnswers.add(inspectionAnswer2);
            inspectionAnswers.add(inspectionAnswer3);
            inspectionAnswers.forEach(answer -> answer.setHint("Пример формулировки для направлений проверок: \n" +
                    "Проверка работы Предприятия осуществляется Правообладателем в следующих направлениях:  \n" +
                    "соблюдение единой символики, стиля и правил использования Знаков Правообладателя;\n" +
                    "соблюдение технологий работы Предприятия и правил обслуживания клиентов.)\n\nПример формулировки для форм проверок:\n" +
                    "Контроль за соблюдением качества работы Предприятия Правообладатель осуществляет следующими способами: \n" +
                    "посещение Предприятия представителями Правообладателя;\n" +
                    "контрольное приобретение услуги Пользователя;\n" +
                    "собеседование с сотрудниками Предприятия;\n" +
                    "проверка документации, связанной с презентацией услуг Предприятия;\n" +
                    "проверка наличия у Пользователя Документации и выполнения требований Правообладателя по его использованию.\n"));

            inspectionQuestion.setAnswers(inspectionAnswers);
            questionRepository.save(inspectionQuestion);

            // Категория ИЗМЕНЕНИЯ В ИСКЛЮЧИТЕЛЬНЫХ ПРАВАХ
            Question specificLawsQuestion = new Question();
            specificLawsQuestion.setTitle("ИЗМЕНЕНИЯ В ИСКЛЮЧИТЕЛЬНЫХ ПРАВАХ");
            specificLawsQuestion.setQuestionText("Является ли переход к другому лицу какого-либо исключительного права, указанного в Вашем договоре, основанием для изменения или расторжения договора?");
            specificLawsQuestion.setQuestionHint("Обычно это условие содержится в пункте «Действие договора»");

            List<Answer> specificLawsAnswers = new ArrayList<>();

            // 1
            Answer specificLawsAnswer1 = new Answer();
            specificLawsAnswer1.setText("Прописано, что договор прекращает действие в случае прекращения принадлежащих Правообладателю прав на фирменное наименование или коммерческое обозначение без замены их новыми аналогичными правами.");
            specificLawsAnswer1.setRisk("Низкий");
            specificLawsAnswer1.setExplanation("Вы обезопашены от ситуации, когда исключительные права могут перейти лицу, в чьей добросовестности Вы не уверены.");
            specificLawsAnswer1.setNote("Рекомендуем Вам прописать в договоре обязанность Правообладателя поддерживать в силе свои исключительные права.");
            specificLawsAnswer1.setQuestion(specificLawsQuestion);

            // 2
            Answer specificLawsAnswer2 = new Answer();
            specificLawsAnswer2.setText("Прописано, что переход к другому лицу какого-либо исключительного права, указанного в настоящем договоре, не является основанием для изменения или расторжения договора. Новый правообладатель становится стороной настоящего договора в части прав и обязанностей, относящихся к перешедшему исключительному праву.");
            specificLawsAnswer2.setRisk("Высокий");
            specificLawsAnswer2.setExplanation("В случае перехода исключительного права другому лицу, он становиться участником Вашего договора. Исключительные права могут перейти лицу, в чьей добросовестности Вы не уверены, но Вы будете обязаны наладить с ним коммерческие отношения.");
            specificLawsAnswer2.setNote("Рекомендуем Вам прописать в договоре обязанность Правообладателя поддерживать в силе свои исключительные права.");
            specificLawsAnswer2.setQuestion(specificLawsQuestion);

            specificLawsAnswers.add(specificLawsAnswer1);
            specificLawsAnswers.add(specificLawsAnswer2);
            specificLawsQuestion.setAnswers(specificLawsAnswers);
            questionRepository.save(specificLawsQuestion);

            // Категория 11: Срок действия договора
            Question durationQuestion = new Question();
            durationQuestion.setTitle("СРОК ДЕЙСТВИЯ ДОГОВОРА");
            durationQuestion.setQuestionText("Установлен ли в Вашем договоре срок его действия?");

            List<Answer> durationAnswers = new ArrayList<>();

            // Ответ 11.1
            Answer durationAnswer1 = new Answer();
            durationAnswer1.setText("Да, срок действия договора до 2 лет и менее");
            durationAnswer1.setRisk("Высокий");
            durationAnswer1.setExplanation("Риск экономических потерь, а также риск купить франшизу, которая не приносит реальной прибыли, что за такой промежуток времени это распознать будет сложно (как правило такой срок договора характерен для франшизы-пустышки)");
            durationAnswer1.setQuestion(durationQuestion);

            // Ответ 11.2
            Answer durationAnswer2 = new Answer();
            durationAnswer2.setText("Срок действия договора от 2 до 5 лет");
            durationAnswer2.setRisk("Средний");
            durationAnswer2.setExplanation("Имеется определенный риск в части окупаемости франшизы (экономический риск).");
            durationAnswer2.setQuestion(durationQuestion);

            // Ответ 11.3
            Answer durationAnswer3 = new Answer();
            durationAnswer3.setText("Срок действия договора от 5 до 10 лет");
            durationAnswer3.setRisk("Низкий");
            durationAnswer3.setExplanation("Как правило, условие о таком сроке будет наилучшим для франчайзи, так как за период действия такого договора наиболее вероятна успешная окупаемость и максимальная прибыль");
            durationAnswer3.setHint("Срок действия франшизы не превышает срок действия товарного знака. Любой товарный знак действует не более 10 лет. После этого срока необходимо продлять его регистрацию. Договор франшизы может быть заключен только на срок до окончания действия знака. Далее регистрация знака и действие договора франшизы могут быть продлены.");
            durationAnswer3.setNote("""
                    Наличие указания срока действия договора влияет на односторонний отказ, а точнее – на срок уведомления (в таком случае – за 30 дней).
                    Право на односторонний отказ от договора коммерческой концессии предусмотрено <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102490&cacheid=88079D8EECE7984AE79105A16555B64F&mode=splus&rnd=5Btq4g#rY9zshU4SdHlxWwp" target="_blank">п. 2 ст. 1037 ГК РФ</a>, согласно которому, каждая из сторон договора коммерческой концессии, заключенного на определенный срок во всякое время вправе отказаться от договора, уведомив об этом другую сторону не позднее чем за тридцать дней, ЕСЛИ договором предусмотрена возможность его прекращения уплатой денежной суммы, установленной в качестве отступного.
                    """);
            durationAnswer3.setQuestion(durationQuestion);

            // Ответ 11.4
            Answer durationAnswer4 = new Answer();
            durationAnswer4.setText("Нет");
            durationAnswer4.setRisk("Высокий");
            durationAnswer4.setExplanation("Договор лучше заключать на определенный срок, чтобы сохранилась гарантия преимущественного права заключить договор на новый срок, а также, чтобы сократить экономические риски.");
            durationAnswer4.setHint("Франчайзи имеет преимущественное право на заключение нового договора коммерческой концессии в течение 3 лет с момента окончания срока действия первоначального договора");
            durationAnswer4.setNote("""
                    Отсутствие указания срока действия договора влияет на односторонний отказ, а точнее – на срок уведомления (в таком случае – за 6 месяцев ИЛИ можно договором предусмотреть более продолжительный срок)
                    Также, если договор бессрочный и установлено отступное за выход из него, срок уведомления 30 дней
                    Право на односторонний отказ от договора коммерческой концессии предусмотрено <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102490&cacheid=88079D8EECE7984AE79105A16555B64F&mode=splus&rnd=5Btq4g#rY9zshU4SdHlxWwp" target="_blank">п. 1 ст. 1037 ГК РФ</a>, согласно которому каждая из сторон договора коммерческой концессии, заключенного без указания срока его действия, во всякое время вправе отказаться от договора, уведомив об этом другую сторону за ШЕСТЬ МЕСЯЦЕВ, если договором не предусмотрен более продолжительный срок.
                    А также, согласно <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=102490&cacheid=88079D8EECE7984AE79105A16555B64F&mode=splus&rnd=5Btq4g#rY9zshU4SdHlxWwp" target="_blank">п.2 ст. 1037</a>, каждая из сторон договора коммерческой концессии, заключенного без указания срока его действия, во всякое время вправе отказаться от договора, уведомив об этом другую сторону не позднее чем за ТРИДЦАТЬ ДНЕЙ, ЕСЛИ договором предусмотрена возможность его прекращения уплатой денежной суммы, установленной в качестве ОТСТУПНОГО.
                    """);
            durationAnswer4.setQuestion(durationQuestion);

            durationAnswers.add(durationAnswer1);
            durationAnswers.add(durationAnswer2);
            durationAnswers.add(durationAnswer3);
            durationAnswers.add(durationAnswer4);
            durationQuestion.setAnswers(durationAnswers);
            questionRepository.save(durationQuestion);

            // Категория 12: Заключение договора на новый срок
            Question renewalQuestion = new Question();
            renewalQuestion.setTitle("ЗАКЛЮЧЕНИЕ ДОГОВОРА НА НОВЫЙ СРОК");
            renewalQuestion.setQuestionText("Как в Вашем договоре сформулировано условие о его пролонгации?");
            renewalQuestion.setQuestionHint("Обычно это условие указывается пункте “Срок действия договора”");

            List<Answer> renewalAnswers = new ArrayList<>();

            // Ответ 12.1
            Answer renewalAnswer1 = new Answer();
            renewalAnswer1.setText("Прописано, что, если во время действия договора Пользователем не было допущено существенных нарушений его положений, которые могли бы привести к его досрочному прекращению, Пользователь имеет по истечении срока настоящего договора право на его заключение на новый срок на тех же условиях.");
            renewalAnswer1.setRisk("Низкий");
            renewalAnswer1.setExplanation("В таком случае меньше вероятность потерять вложенные в бизнес деньги и появляется возможность при желании продлить действие договора и продолжать работать под брендом.\n");
            renewalAnswer1.setQuestion(renewalQuestion);

            // Ответ 12.2
            Answer renewalAnswer2 = new Answer();
            renewalAnswer2.setText("Правообладатель вправе отказать в заключении договора коммерческой концессии на новый срок при условии, что в течение трех лет со дня истечения срока настоящего договора он не будет заключать с другими лицами аналогичные договоры коммерческой концессии и соглашаться на заключение аналогичных договоров коммерческой субконцессии, действие которых будет распространяться на ту же территорию, на которой действовал настоящий договор");
            renewalAnswer2.setRisk("Высокий");
            renewalAnswer2.setExplanation("В таком случае по истечению срока действия договора Вы рискуете полностью потерять свой бизнес, так как не сможете пользоваться товарным знаком, фирменным наименованием иными результатами интеллектуальной деятельности, переданными Вам по договору франчайзинга.");
            renewalAnswer2.setNote("""
                    Согласно <a href="https://www.consultant.ru/cons/cgi/online.cgi?req=doc&base=LAW&n=493202&dst=86&cacheid=B7007E1D1ECD0FFB7AF83588B3AA52F5&mode=splus&rnd=5Btq4g#5HIzshUQL5TGERN41" target="_blank">ст. 1035 ГК РФ</a> у Вас возникает преимущественное право на пролонгацию этого договора в будущем. Если в течение года правообладатель (франчайзер) заключает договор с кем-то другим, Вы имеете право через суд потребовать перевода на себя прав и обязанностей по заключенному договору и возмещения убытков, причиненных отказом возобновить с ним договор коммерческой концессии, или только возмещения таких убытков
                    """);
            renewalAnswer2.setQuestion(renewalQuestion);

            renewalAnswers.add(renewalAnswer1);
            renewalAnswers.add(renewalAnswer2);
            renewalQuestion.setAnswers(renewalAnswers);
            questionRepository.save(renewalQuestion);

            // Категория 13: Судьба оборудования при прекращении договора
            Question equipmentFateQuestion = new Question();
            equipmentFateQuestion.setTitle("СУДЬБА ОБОРУДОВАНИЯ И НЕРЕАЛИЗОВАННОЙ ПРОДУКЦИИ ПРИ ПРЕКРАЩЕНИИ ДОГОВОРА ");
            equipmentFateQuestion.setQuestionText("Как в Вашем договоре определяется судьба оборудования при прекращении договора?");

            List<Answer> equipmentFateAnswers = new ArrayList<>();

            // Ответ 13.1
            Answer equipmentFateAnswer1 = new Answer();
            equipmentFateAnswer1.setText("Выкуп оборудования и нереализованной продукции");
            equipmentFateAnswer1.setRisk("Средний");
            equipmentFateAnswer1.setExplanation("Риск заключается в том, что если Правообладатель не проявит интерес к выкупу, франчайзи может остаться с нереализованной продукцией и оборудованием, что приведет к финансовым потерям.");
            equipmentFateAnswer1.setHint("(*примерная формулировка условия: Пользователь обязан в течение _ дней после прекращения действия настоящего Договора предоставить Правообладателю информацию о количестве и ассортименте нереализованной Продукции. Правообладатель имеет право выкупить указанную Продукцию и Торговое оборудование по остаточной стоимости на момент прекращения Договора. Если Правообладатель не уведомит Пользователя о намерении выкупа в течение _ дней, Пользователь вправе реализовать Продукцию и оборудование по своему усмотрению)");
            equipmentFateAnswer1.setQuestion(equipmentFateQuestion);

            // Ответ 13.2
            Answer equipmentFateAnswer2 = new Answer();
            equipmentFateAnswer2.setText("Удаление знаков правообладателя и реализация");
            equipmentFateAnswer2.setRisk("Низкий");
            equipmentFateAnswer2.setExplanation("Возможность реализации продукции без знаков правообладателя снижает риск потерь, однако франчайзи должен быть осторожен с соблюдением условий, чтобы избежать юридических последствий.");
            equipmentFateAnswer2.setHint("(*примерная формулировка условия:  Пользователь вправе реализовать нереализованную Продукцию, находящуюся на складе и в витрине Торгового оборудования, при условии удаления Знаков Правообладателя. Пользователь также обязан вернуть все неиспользованные предметы с Знаками Правообладателя в течение _ дней)");
            equipmentFateAnswer2.setQuestion(equipmentFateQuestion);

            // Ответ 13.3
            Answer equipmentFateAnswer3 = new Answer();
            equipmentFateAnswer3.setText("Обязанность возврата оборудования");
            equipmentFateAnswer3.setRisk("Высокий");
            equipmentFateAnswer3.setExplanation("В этом случае франчайзи рискует понести значительные убытки, если не сможет вернуть оборудование или продукцию в установленный срок, что может привести к дополнительным финансовым затратам и юридическим последствиям.");
            equipmentFateAnswer3.setHint("(*примерная формулировка условия:  Пользователь обязан вернуть все Торговое оборудование и нереализованную Продукцию Правообладателю в течение _ дней после прекращения действия Договора, за свой счет. В случае невыполнения этого условия, Пользователь несет ответственность за убытки, понесенные Правообладателем)");
            equipmentFateAnswer3.setQuestion(equipmentFateQuestion);

            // Ответ 13.4
            Answer equipmentFateAnswer4 = new Answer();
            equipmentFateAnswer4.setText("Согласование условий выкупа");
            equipmentFateAnswer4.setRisk("Средний");
            equipmentFateAnswer4.setExplanation("Согласование условий выкупа может привести к выгодной сделке, но также существует риск, что франчайзи не сможет реализовать продукцию, если Правообладатель откажется от выкупа.");
            equipmentFateAnswer4.setHint("(*примерная формулировка условия: Пользователь обязан в течение _ дней после прекращения действия Договора предоставить Правообладателю информацию о нереализованной Продукции. Правообладатель имеет право выкупить Продукцию по цене оптовой поставки со скидкой не менее _%. Если Правообладатель не уведомит о намерении выкупа, Пользователь может реализовать Продукцию с удалением Знаков Правообладателя)");
            equipmentFateAnswer4.setQuestion(equipmentFateQuestion);

            // Ответ 13.5
            Answer equipmentFateAnswer5 = new Answer();
            equipmentFateAnswer5.setText("Уничтожение неиспользуемых предметов");
            equipmentFateAnswer5.setRisk("Высокий");
            equipmentFateAnswer5.setExplanation("Уничтожение продукции может привести к значительным финансовым потерям, особенно если франчайзи не успеет реализовать продукцию до ее уничтожения.");
            equipmentFateAnswer5.setHint("(*примерная формулировка условия:  Пользователь обязан в течение _ дней уничтожить все неиспользованные предметы с Знаками Правообладателя по требованию Правообладателя, с составлением акта об уничтожении. В противном случае Пользователь несет ответственность за убытки.)");
            equipmentFateAnswer5.setQuestion(equipmentFateQuestion);


            equipmentFateAnswers.add(equipmentFateAnswer1);
            equipmentFateAnswers.add(equipmentFateAnswer2);
            equipmentFateAnswers.add(equipmentFateAnswer3);
            equipmentFateAnswers.add(equipmentFateAnswer4);
            equipmentFateAnswers.add(equipmentFateAnswer5);
            equipmentFateQuestion.setAnswers(equipmentFateAnswers);
            questionRepository.save(equipmentFateQuestion);

            // Категория 14: Обеспечение конфиденциальности
            Question confidentialityQuestion = new Question();
            confidentialityQuestion.setTitle("ОБЕСПЕЧЕНИЕ КОНФИДЕНЦИАЛЬНОСТИ");
            confidentialityQuestion.setQuestionText("Как в Вашем договоре регламентируется обязанность по защите конфиденциальных данных?");
            confidentialityQuestion.setQuestionHint("Обычно это условие содержится в пункте “Права и обязанности сторон”");

            List<Answer> confidentialityAnswers = new ArrayList<>();

            // Ответ 14.1
            Answer confidentialityAnswer1 = new Answer();
            confidentialityAnswer1.setText("Обязательства по соблюдению конфиденциальности, предусмотренные данным разделом Договора, действуют в течение срока действия Договора, а также в течении нескольких лет с момента прекращения Договора.");
            confidentialityAnswer1.setRisk("Средний");
            confidentialityAnswer1.setExplanation("Вы не сможете использовать или разглашать полученные данные в течение определенного количества времени, которое указано в договоре, даже после его расторжения или истечения срока действия");
            confidentialityAnswer1.setQuestion(confidentialityQuestion);

            // Ответ 14.2
            Answer confidentialityAnswer2 = new Answer();
            confidentialityAnswer2.setText("Пользователь обязан защищать Конфиденциальную информацию, в том числе принимать все необходимые меры для защиты ее от несанкционированного доступа");
            confidentialityAnswer2.setRisk("Высокий");
            confidentialityAnswer2.setExplanation("Обеспечение защиты данных может быть затратным для Вашего бизнеса. Также в случае утечки конфиденциальной информации, Вы будете нести ответственность.");
            confidentialityAnswer2.setNote("""
                    Рекомендуем Вам согласовать с Правообладателем следующие моменты:
                    — Конкретизировать меры в договоре (например, указать необходимость VPN, двухфакторной аутентификации, регулярного обучения сотрудников).
                    — Ссылаться на стандарты (например, NIST, ISO 27001) вместо расплывчатых формулировок.
                    — Включить пункт о разделении ответственности при форс-мажорах (кибератаки, действия третьих лиц).
                    — Предусмотреть финансовую поддержку от франчайзера на внедрение мер безопасности.
                    """);
            confidentialityAnswer2.setQuestion(confidentialityQuestion);

            confidentialityAnswers.add(confidentialityAnswer1);
            confidentialityAnswers.add(confidentialityAnswer2);
            confidentialityQuestion.setAnswers(confidentialityAnswers);
            questionRepository.save(confidentialityQuestion);

            // Категория 15: Условия об отсутствии гарантии правообладателя успешности бизнеса
            Question guaranteesQuestion = new Question();
            guaranteesQuestion.setTitle("УСЛОВИЯ ОБ ОТСУТСТВИИ ГАРАНТИИ ПРАВООБЛАДАТЕЛЕМ УСПЕШНОСТИ БИЗНЕСА");
            guaranteesQuestion.setQuestionText("Содержится ли в договоре условие об отсутствии гарантий успешности бизнеса?");
            guaranteesQuestion.setQuestionHint("ПРИМЕРЫ ФОРМУЛИРОВКИ УСЛОВИЯ:\n" +
                    "Пользователь признает, что успех и прибыльность коммерческой деятельности, осуществляемой в рамках настоящего Договора сопряжена с экономическими рисками и зависит от его предпринимательских способностей.\n" +
                    "Правообладатель настоящим заявляет о том, что он не давал, а Пользователь признает, что не получал и не имеет права на получение какого-либо обязательства или гарантии, как прямо выраженного, так и предлагаемого, в отношении возможных результатов работы Предприятия и успеха предпринимательской деятельности, осуществляемой в рамках настоящего Договора.\n" +
                    "При предоставлении согласований, рекомендаций, одобрений в рамках настоящего Договора Правообладатель, прежде всего, оценивает соответствие действий и/или документов, для которых испрашивается такое согласование и/или одобрение, требованиям Системы, Стандартам и Руководству и не несет обязанности по рассмотрению вопросов целесообразности и экономической выгоды, а также иных вопросов, которые подлежат оценке Пользователем, если только иное прямо не предусмотрено настоящим Договором. \n" +
                    "В любом случае согласование, рекомендации, одобрение не является какой-либо гарантией со стороны Правообладателя.\n");

            List<Answer> guaranteesAnswers = new ArrayList<>();

            // Ответ 15.1
            Answer guaranteesAnswer1 = new Answer();
            guaranteesAnswer1.setText("Да");
            guaranteesAnswer1.setRisk("Высокий");
            guaranteesAnswer1.setExplanation("Наличие подобного условия может свидетельствовать о повышенных экономических рисках деятельности, права на осуществление которой по настоящему договору приобретает Пользователь. Правообладатель специально подчеркивает, что не дает гарантий в отношении возможных результатов работы Предприятия и успеха предпринимательской деятельности, осуществляемой в рамках настоящего Договора.");
            guaranteesAnswer1.setQuestion(guaranteesQuestion);

            // Ответ 15.2
            Answer guaranteesAnswer2 = new Answer();
            guaranteesAnswer2.setText("Нет");
            guaranteesAnswer2.setRisk("Средний");
            guaranteesAnswer2.setExplanation(
                    """
                            Отсутствие приведенных выше условий лучше, чем их наличие, однако их отсутствие не является гарантией успешности бизнеса, необходимо предусмотреть некоторые гарантии по договору <a href="/memo/assurances" target="_blank">(см. памятку “о заверениях правообладателя”)</a>
                            """);
            guaranteesAnswer2.setQuestion(guaranteesQuestion);

            guaranteesAnswers.add(guaranteesAnswer1);
            guaranteesAnswers.add(guaranteesAnswer2);
            guaranteesQuestion.setAnswers(guaranteesAnswers);
            questionRepository.save(guaranteesQuestion);

            // Категория 16: Подсудность
            Question jurisdictionQuestion = new Question();
            jurisdictionQuestion.setTitle("ПОДСУДНОСТЬ");
            jurisdictionQuestion.setQuestionText("Какой суд для разрешения споров указан в Вашем договоре?");
            jurisdictionQuestion.setQuestionHint("Обычно это условия содержится в пункте “Порядок разрешения споров”");

            List<Answer> jurisdictionAnswers = new ArrayList<>();

            // Ответ 16.1
            Answer jurisdictionAnswer1 = new Answer();
            jurisdictionAnswer1.setText("Арбитражный суд по месту нахождения Пользователя");
            jurisdictionAnswer1.setRisk("Низкий");
            jurisdictionAnswer1.setExplanation("В таком случае Вам будет удобнее и менее затратно разрешать споры в суде по своему местонахождению.");
            jurisdictionAnswer1.setQuestion(jurisdictionQuestion);

            // Ответ 16.2
            Answer jurisdictionAnswer2 = new Answer();
            jurisdictionAnswer2.setText("Арбитражный суд по месту нахождения Правообладателя");
            jurisdictionAnswer2.setRisk("Средний");
            jurisdictionAnswer2.setExplanation("Суд по месту нахождения Правообладателя может находиться на большой удаленности от Вас.  Это может повлечь дополнительные сложности и материальные расходы (например, транспортные и расходы на представительство в другом регионе) ");
            jurisdictionAnswer2.setNote("Попробуйте согласовать с Правообладателем условие об изменении подсудности по Вашему местонахождению. Вероятно, Правообладатель согласится изменить подсудность, установленную в договоре, если Вы об этом попросите. Если же Правообладатель отказывается изменить этот пункт, отнеситесь к договору особенно тщательно: возможно, имеются нежелательные для вас обстоятельства, о которых вам неизвестно, или просто откажитесь от сделки, подыскав другую франшизу.");
            jurisdictionAnswer2.setQuestion(jurisdictionQuestion);

            jurisdictionAnswers.add(jurisdictionAnswer1);
            jurisdictionAnswers.add(jurisdictionAnswer2);
            jurisdictionQuestion.setAnswers(jurisdictionAnswers);
            questionRepository.save(jurisdictionQuestion);

            log.info("Данные успешно загружены в базу данных");
        };
    }
}
