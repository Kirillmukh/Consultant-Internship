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

            // Ответ 1.1
            Answer territoryAnswer1 = new Answer();
            territoryAnswer1.setText("Да, в пределах одного района (или даже улицы)");
            territoryAnswer1.setRisk("Высокий");
            territoryAnswer1.setExplanation("В таком случае Вы (франчайзи) сильно ограничены в выборе места осуществления бизнеса. Выбор удачного места расположения кофейни является ключевым фактором в успехе такой франшизы, поэтому такое условие будет невыгодным для Вас (франчайзи)");
            territoryAnswer1.setQuestion(territoryQuestion);

            // Ответ 1.2
            Answer territoryAnswer2 = new Answer();
            territoryAnswer2.setText("Да, в пределах одного города");
            territoryAnswer2.setRisk("Средний");
            territoryAnswer2.setExplanation("В таком случае Вы (франчайзи) остается ограничен в выборе места осуществления бизнеса, что может создать некоторые проблемы, например, с поиском подходящего помещения.");
            territoryAnswer2.setNote("ОБРАТИТЕ ВНИМАНИЕ! каждый случай индивидуален, и, если Вы уверены в своем контрагенте или, к примеру, данная франшиза довольно успешна в пределах этого города, то условие может оказаться выигрышным.");
            territoryAnswer2.setQuestion(territoryQuestion);

            // Ответ 1.3
            Answer territoryAnswer3 = new Answer();
            territoryAnswer3.setText("Да, в пределах одного субъекта федерации");
            territoryAnswer3.setRisk("Средний");
            territoryAnswer3.setExplanation("В таком случае Вы (франчайзи) остаетесь довольно ограниченны в выборе места осуществления бизнеса");
            territoryAnswer3.setNote("ОБРАТИТЕ ВНИМАНИЕ! Каждый случай индивидуален, и, если Вы уверены в своем контрагенте или, к примеру, данная франшиза довольно успешна в пределах этого субъекта федерации, то условие может оказаться выигрышным.");
            territoryAnswer3.setQuestion(territoryQuestion);

            // Ответ 1.4
            Answer territoryAnswer4 = new Answer();
            territoryAnswer4.setText("Да, в пределах страны");
            territoryAnswer4.setRisk("Низкий");
            territoryAnswer4.setExplanation("В таком случае, Вам (франчайзи) доступен широкий выбор места ведения бизнеса без привязки в какой-либо территории, кроме страны.");
            territoryAnswer4.setNote("ОБРАТИТЕ ВНИМАНИЕ! Каждый случай индивидуален, и за Вами, в таком случае, остается выбор удачного места расположения кофейни, что не лишает Вас риска не преуспеть в бизнесе.");
            territoryAnswer4.setQuestion(territoryQuestion);

            // Ответ 1.5
            Answer territoryAnswer5 = new Answer();
            territoryAnswer5.setText("Нет");
            territoryAnswer5.setRisk("Низкий");
            territoryAnswer5.setExplanation("Такое условие является наиболее оптимальным вариантом, так как Вам (франчайзи) доступен широкий выбор места ведения бизнеса без привязки в какой-либо территории.");
            territoryAnswer5.setQuestion(territoryQuestion);

            territoryAnswers.add(territoryAnswer1);
            territoryAnswers.add(territoryAnswer2);
            territoryAnswers.add(territoryAnswer3);
            territoryAnswers.add(territoryAnswer4);
            territoryAnswers.add(territoryAnswer5);

            territoryQuestion.setAnswers(territoryAnswers);
            questionRepository.save(territoryQuestion);

            // Категория 2: Размер выплачиваемого пользователем правообладателю ежемесячного вознаграждения (роялти)
            Question royaltyQuestion = new Question();
            royaltyQuestion.setTitle("РАЗМЕР ВЫПЛАЧИВАЕМОГО ПОЛЬЗОВАТЕЛЕМ ПРАВООБЛАДАТЕЛЮ ЕЖЕМЕСЯЧНОГО ВОЗНАГРАЖДЕНИЯ (РОЯЛТИ)");
            royaltyQuestion.setQuestionText("Каким образом, согласно Вашему договору, рассчитывается сумма вознаграждения для правообладателя?");

            List<Answer> royaltyAnswers = new ArrayList<>();

            // Ответ 2.1
            Answer royaltyAnswer1 = new Answer();
            royaltyAnswer1.setText("Определенный процент от выручки (оборота) (пример формулировки: Роялти составляет X% от валовой выручки (без вычета расходов): Роялти меньше 5%");
            royaltyAnswer1.setRisk("Средний");
            royaltyAnswer1.setExplanation("Несмотря на то, что процент роялти экономически выгоден для франчайзи, появляется риск того, что франчайзер не будет заинтересован в том, чтобы помогать развивать бизнес, так как больших роялти с него он не получает");
            royaltyAnswer1.setNote("ОБРАТИТЕ ВНИМАНИЕ! Каждый случай индивидуален, и этот параметр может оказаться с низким риском, если Вы уверены в своем контрагенте)");
            royaltyAnswer1.setQuestion(royaltyQuestion);

            // Ответ 2.2
            Answer royaltyAnswer2 = new Answer();
            royaltyAnswer2.setText("Определенный процент от выручки (оборота): Роялти в диапазоне от 5 до 10%");
            royaltyAnswer2.setRisk("Низкий");
            royaltyAnswer2.setExplanation("Анализ рынка франшиз кофеен показал, что в среднем самый экономически выгодный для обеих сторон процент роялти составляет от 5 до 10%.");
            royaltyAnswer2.setQuestion(royaltyQuestion);

            // Ответ 2.3
            Answer royaltyAnswer3 = new Answer();
            royaltyAnswer3.setText("Определенный процент от выручки (оборота): Роялти больше 10-20%");
            royaltyAnswer3.setRisk("Высокий");
            royaltyAnswer3.setExplanation("Слишком большой процент ежемесячных роялти тормозит развитие бизнеса и увеличивает срок окупаемости франшизы.");
            royaltyAnswer3.setNote("ОБРАТИТЕ ВНИМАНИЕ! Помимо уплаты роялти, Вам, как франчайзи, придется нести другие расходы для ведения бизнеса, поэтому роялти не должны превышать 1/10 прибыли Вашего бизнеса.");
            royaltyAnswer3.setQuestion(royaltyQuestion);

            // Ответ 2.4
            Answer royaltyAnswer4 = new Answer();
            royaltyAnswer4.setText("Определенный процент от прибыли (пример формулировки: Роялти составляет X% от чистой прибыли (после вычета всех расходов): Роялти меньше 5%");
            royaltyAnswer4.setRisk("Средний");
            royaltyAnswer4.setExplanation("Несмотря на то, что процент роялти экономически выгоден для франчайзи, появляется риск того, что франчайзер не будет заинтересован в том, чтобы помогать развивать бизнес, так как больших роялти с него он не получает");
            royaltyAnswer4.setNote("ОБРАТИТЕ ВНИМАНИЕ! Каждый случай индивидуален, и этот параметр может оказаться с низким риском, если Вы уверены в своем контрагенте)");
            royaltyAnswer4.setQuestion(royaltyQuestion);

            // Ответ 2.5
            Answer royaltyAnswer5 = new Answer();
            royaltyAnswer5.setText("Определенный процент от прибыли: Роялти в диапазоне от 5 до 10%");
            royaltyAnswer5.setRisk("Низкий");
            royaltyAnswer5.setExplanation("Анализ рынка франшиз кофеен показал, что в среднем самый экономически выгодный для обеих сторон процент роялти составляет от 5 до 10%.");
            royaltyAnswer5.setQuestion(royaltyQuestion);

            // Ответ 2.6
            Answer royaltyAnswer6 = new Answer();
            royaltyAnswer6.setText("Определенный процент от прибыли: Роялти больше 10-20%");
            royaltyAnswer6.setRisk("Высокий");
            royaltyAnswer6.setExplanation("Слишком большой процент ежемесячных роялти тормозит развитие бизнеса и увеличивает срок окупаемости франшизы.");
            royaltyAnswer6.setNote("ОБРАТИТЕ ВНИМАНИЕ! Помимо уплаты роялти, Вам, как франчайзи, придется нести другие расходы для ведения бизнеса, поэтому роялти не должны превышать 1/10 прибыли Вашего бизнеса.");
            royaltyAnswer6.setQuestion(royaltyQuestion);

            // Ответ 2.7
            Answer royaltyAnswer7 = new Answer();
            royaltyAnswer7.setText("Фиксированная ставка (пример формулировки - Роялти по настоящему договору определяются фиксированной суммой (например, 50 000 руб./мес.), независимо от выручки)");
            royaltyAnswer7.setRisk("Высокий (на старте), Низкий (при стабильности)");
            royaltyAnswer7.setExplanation("Преимуществами для франчайзи являются предсказуемость расходов (легче планировать бюджет) и отсутствие зависимости от прибыльности бизнеса. Неблагоприятными моментами являются высокий в начале ведения бизнеса риск - если бизнес не вышел на окупаемость, фиксированные платежи могут быть тяжелыми");
            royaltyAnswer7.setNote("ОБРАТИТЕ ВНИМАНИЕ! Это условие может иметь низкий риск при стабильном доходе бизнеса - когда бизнес работает устойчиво (не для новичков в франчайзинге).");
            royaltyAnswer7.setQuestion(royaltyQuestion);

            // Ответ 2.8
            Answer royaltyAnswer8 = new Answer();
            royaltyAnswer8.setText("Смешанные (фиксированная сумма + проценты)");
            royaltyAnswer8.setRisk("Средний");
            royaltyAnswer8.setExplanation("Преимуществом для франчайзи является гарантированный минимум для франчайзера, но гибкость при росте продаж. Неблагоприятным моментом можно назвать невыгодность такого условия при низкой маржинальности бизнеса. Такое условие сочетает риски фиксированных и процентных платежей.");
            royaltyAnswer8.setQuestion(royaltyQuestion);

            // Ответ 2.9
            Answer royaltyAnswer9 = new Answer();
            royaltyAnswer9.setText("Роялти через обязательные закупки (Пользователь обязан закупать товары/услуги у франчайзера, а роялти включена в их стоимость)");
            royaltyAnswer9.setRisk("Высокий");
            royaltyAnswer9.setExplanation("Преимуществом для франчайзи является отсутствие отдельного регулярного платежа - такое условие называют также \"скрытой\" формой роялти. Минусом является зависимость франчайзи от франчайзера в поставках.");
            royaltyAnswer9.setNote("ОБРАТИТЕ ВНИМАНИЕ! Каждый случай индивидуален, и необходимо дополнительно согласовывать данное условие с контрагентом, так как, если закупочные цены завышены, снижается рентабельность бизнеса и выгода франчайзи.");
            royaltyAnswer9.setQuestion(royaltyQuestion);

            // Ответ 2.10
            Answer royaltyAnswer10 = new Answer();
            royaltyAnswer10.setText("Отсутствие роялти (только паушальный взнос) (Пользователь платит только первоначальный взнос, а далее - без регулярных отчислений)");
            royaltyAnswer10.setRisk("Низкий");
            royaltyAnswer10.setExplanation("Преимуществом для франчайзи является отсутствие постоянной финансовой нагрузки. Минусом является то, что франчайзер может меньше поддерживать бизнес, так как не имеет в нем финансовой выгоды.");
            royaltyAnswer10.setQuestion(royaltyQuestion);

            royaltyAnswers.add(royaltyAnswer1);
            royaltyAnswers.add(royaltyAnswer2);
            royaltyAnswers.add(royaltyAnswer3);
            royaltyAnswers.add(royaltyAnswer4);
            royaltyAnswers.add(royaltyAnswer5);
            royaltyAnswers.add(royaltyAnswer6);
            royaltyAnswers.add(royaltyAnswer7);
            royaltyAnswers.add(royaltyAnswer8);
            royaltyAnswers.add(royaltyAnswer9);
            royaltyAnswers.add(royaltyAnswer10);

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
            paymentChangeAnswer1.setExplanation("Франчайзер может повысить платежи даже в период кризиса или падения спроса, не учитывая проблемы франчайзи. Кроме того, могут возникнуть проблемы с планированием бюджета из-за неожиданного повышения размера вознаграждения");
            paymentChangeAnswer1.setNote("ОБРАТИТЕ ВНИМАНИЕ: Рекомендуем Вам согласовать это условие так, чтобы Правообладатель мог повышать размер платежей не чаще чем 1 раз в год (можно установить и иную периодичность изменения вознаграждения). Также рекомендуем прописать условие об обязательности уведомления Вас Правообладателем за определенный срок, в случае если он планирует повысить платежи. Также советуем ограничить возможность повышения платежей определенным процентом (например, не более чем на 10%).");
            paymentChangeAnswer1.setQuestion(paymentChangeQuestion);

            // Ответ 3.2
            Answer paymentChangeAnswer2 = new Answer();
            paymentChangeAnswer2.setText("Правообладатель вправе изменять размер периодических платежей в течение срока действия настоящего Договора, но частота изменений ограничена (например, один раз в год).");
            paymentChangeAnswer2.setRisk("Средний");
            paymentChangeAnswer2.setExplanation("Правообладатель может повысить размер платежей, но не может делать этого произвольно.");
            paymentChangeAnswer2.setNote("ОБРАТИТЕ ВНИМАНИЕ: Рекомендуем Вам прописать условие об обязательности уведомления Вас Правообладателем за определенный срок, в случае если он планирует повысить платежи. Также советуем ограничить возможность повышения платежей определенным процентом (например, не более чем на 10%).");
            paymentChangeAnswer2.setQuestion(paymentChangeQuestion);

            // Ответ 3.3
            Answer paymentChangeAnswer3 = new Answer();
            paymentChangeAnswer3.setText("Правообладатель не вправе изменять размер, формулу расчёта или порядок уплаты вознаграждения в течение всего срока действия Договора.");
            paymentChangeAnswer3.setRisk("Низкий");
            paymentChangeAnswer3.setExplanation("При таком условии снижается риск неосновательного увеличения вознаграждения, что в свою очередь делает расходы франчайзи более предсказуемыми");
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

            List<Answer> registrationAnswers = new ArrayList<>();

            // Ответ 4.1
            Answer registrationAnswer1 = new Answer();
            registrationAnswer1.setText("Правообладатель обязан обеспечить регистрацию настоящего договора в установленном порядке");
            registrationAnswer1.setRisk("Низкий");
            registrationAnswer1.setExplanation("Гражданским Кодексом РФ статьей 1031 по общему правилу обязанность обеспечить регистрацию договора закреплена за Правообладателем.");
            registrationAnswer1.setQuestion(registrationQuestion);

            // Ответ 4.2
            Answer registrationAnswer2 = new Answer();
            registrationAnswer2.setText("Все расходы, связанные с регистрацией настоящего Договора, несет Пользователь");
            registrationAnswer2.setRisk("Высокий");
            registrationAnswer2.setExplanation("Гражданским Кодексом РФ статьей 1031 по общему правилу обязанность обеспечить регистрацию договора закреплена за Правообладателем. Регистрация договора за Ваш счет влечет для Вас возникновение дополнительных финансовых издержек.");
            registrationAnswer2.setNote("ОБРАТИТЕ ВНИМАНИЕ: Рекомендуем Вам согласовать с Правообладателем это условие так, чтобы обязанность обеспечить регистрацию договора возлагалась на Правообладателя.");
            registrationAnswer2.setQuestion(registrationQuestion);

            registrationAnswers.add(registrationAnswer1);
            registrationAnswers.add(registrationAnswer2);
            registrationQuestion.setAnswers(registrationAnswers);
            questionRepository.save(registrationQuestion);

            // Категория 5: Лицензии
            Question licensesQuestion = new Question();
            licensesQuestion.setTitle("ЛИЦЕНЗИИ");
            licensesQuestion.setQuestionText("Указано ли в Вашем договоре обязанность Правообладателя обеспечить оформление лицензий в установленном порядке?");

            List<Answer> licensesAnswers = new ArrayList<>();

            // Ответ 5.1
            Answer licensesAnswer1 = new Answer();
            licensesAnswer1.setText("Прописана обязанность Правообладателя обеспечить оформление лицензий в установленном порядке");
            licensesAnswer1.setRisk("Низкий");
            licensesAnswer1.setExplanation("Гражданским Кодексом РФ статьей 1031 по общему правилу такая обязанность возлагается на Правообладателя.");
            licensesAnswer1.setQuestion(licensesQuestion);

            // Ответ 5.2
            Answer licensesAnswer2 = new Answer();
            licensesAnswer2.setText("Такое условие прописано, но указано, что расходы по оформлению лицензий возлагаются на Пользователя");
            licensesAnswer2.setRisk("Средний");
            licensesAnswer2.setExplanation("Гражданским Кодексом РФ статьей 1031 по общему правилу такая обязанность возлагается на Правообладателя. Необходимость оформления лицензий влечет для Вас возникновение дополнительных финансовых и временных издержек.");
            licensesAnswer2.setNote("ОБРАТИТЕ ВНИМАНИЕ! Рекомендуем Вам согласовать с Правообладателем это условие так, чтобы обязанность обеспечить оформление лицензий возлагалась на Правообладателя.");
            licensesAnswer2.setQuestion(licensesQuestion);

            // Ответ 5.3
            Answer licensesAnswer3 = new Answer();
            licensesAnswer3.setText("Такое условие не прописано");
            licensesAnswer3.setRisk("Высокий");
            licensesAnswer3.setExplanation("Гражданским Кодексом в ст.1031 РФ закреплена обязанность Правообладателя обеспечить проведение консультаций. Отсутствие надлежащим образом оформленных лицензий влечет риск привлечения Вас к юридической ответственности.");
            licensesAnswer3.setNote("ОБРАТИТЕ ВНИМАНИЕ! Рекомендуем Вам согласовать с Правообладателем это условие так, чтобы обязанность обеспечить оформление лицензий возлагалась на Правообладателя.");
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
            pricingAnswer1.setText("Правообладатель устанавливает ценовую политику в отношении Товаров. Пользователь следует единой системе ценообразования");
            pricingAnswer1.setRisk("Высокий");
            pricingAnswer1.setExplanation("Вы не сможете самостоятельно устанавливать цены и должны будете действовать в соответствии с указаниями правообладателя (франчайзера). Существует вероятность того, что Вам придется работать в убыток либо выходить в \"ноль\" без получения прибыли.");
            pricingAnswer1.setQuestion(pricingQuestion);

            // Ответ 6.2
            Answer pricingAnswer2 = new Answer();
            pricingAnswer2.setText("Правообладатель не имеет права определять отпускные цены. Указания об уровне цен могут даваться только в качестве рекомендаций");
            pricingAnswer2.setRisk("Низкий");
            pricingAnswer2.setExplanation("Вы не ограничены в ценообразовании и можете сами выставлять и корректировать цены по своему усмотрению, что будет явным преимуществом для ведения бизнеса.");
            pricingAnswer2.setQuestion(pricingQuestion);

            pricingAnswers.add(pricingAnswer1);
            pricingAnswers.add(pricingAnswer2);
            pricingQuestion.setAnswers(pricingAnswers);
            questionRepository.save(pricingQuestion);

            // Категория 7: Условие о поставках сырья
            Question suppliesQuestion = new Question();
            suppliesQuestion.setTitle("УСЛОВИЕ О ПОСТАВКАХ СЫРЬЯ");
            suppliesQuestion.setQuestionText("Указано ли в Вашем договоре обязанность пользователя закупать продукцию только у Правообладателя или у определенных поставщиков?");

            List<Answer> suppliesAnswers = new ArrayList<>();

            // Ответ 7.1
            Answer suppliesAnswer1 = new Answer();
            suppliesAnswer1.setText("Пользователь обязан приобретать продукцию только у поставщиков, рекомендованных Правообладателем");
            suppliesAnswer1.setRisk("Высокий");
            suppliesAnswer1.setExplanation("Данное условие создает определенные рамки для франчайзи. Ограничение выбора лишает франчайзи возможности влиять на качество товаров. Низкое качество со стороны поставщика может навредить репутации бизнеса. Франчайзер или назначенные поставщики могут устанавливать завышенные цены.");
            suppliesAnswer1.setQuestion(suppliesQuestion);

            // Ответ 7.2
            Answer suppliesAnswer2 = new Answer();
            suppliesAnswer2.setText("Пользователь вправе закупать товар у любых поставщиков по своему выбору, при условии соблюдения требований качества");
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
            equipmentAnswer1.setExplanation("Данное условие создает определенные рамки для франчайзи. Франчайзер может устанавливать завышенные цены на оборудование, так как франчайзи не имеет альтернатив. Кроме того, это оборудование может не соответствовать современным технологиям.");
            equipmentAnswer1.setQuestion(equipmentQuestion);

            // Ответ 8.2
            Answer equipmentAnswer2 = new Answer();
            equipmentAnswer2.setText("Франчайзи вправе закупать оборудование у любых поставщиков по своему выбору, при условии соблюдения правил безопасности");
            equipmentAnswer2.setRisk("Низкий");
            equipmentAnswer2.setExplanation("Вы не ограничены никакими рамками в выборе поставщика оборудования и сырья и сможете приобретать их по выгодному соотношению цены и качества, действуя на свое усмотрение.");
            equipmentAnswer2.setQuestion(equipmentQuestion);

            equipmentAnswers.add(equipmentAnswer1);
            equipmentAnswers.add(equipmentAnswer2);
            equipmentQuestion.setAnswers(equipmentAnswers);
            questionRepository.save(equipmentQuestion);

            // Категория 9: Консультации
            Question consultationsQuestion = new Question();
            consultationsQuestion.setTitle("КОНСУЛЬТАЦИИ");
            consultationsQuestion.setQuestionText("Указано ли в Вашем договоре обязанность Правообладателя консультировать Пользователя по вопросам деятельности франшизы?");

            List<Answer> consultationsAnswers = new ArrayList<>();

            // Ответ 9.1
            Answer consultationsAnswer1 = new Answer();
            consultationsAnswer1.setText("Прописана обязанность Правообладателя консультировать Пользователя по широкому кругу вопросов");
            consultationsAnswer1.setRisk("Низкий");
            consultationsAnswer1.setExplanation("Гражданским Кодексом в ст.1031 РФ закреплена обязанность Правообладателя обеспечить проведение консультаций. Если прописан широкий круг направлений консультирования, Правообладатель не сможет отказаться от дачи советов по указанным вопросам.");
            consultationsAnswer1.setQuestion(consultationsQuestion);

            // Ответ 9.2
            Answer consultationsAnswer2 = new Answer();
            consultationsAnswer2.setText("Прописана обязанность Правообладателя консультировать Пользователя, но не указано, по каким именно вопросам");
            consultationsAnswer2.setRisk("Средний");
            consultationsAnswer2.setExplanation("Гражданским Кодексом в ст.1031 РФ закреплена обязанность Правообладателя обеспечить проведение консультаций. Если в договоре не указаны конкретные направления консультаций, между Вами и Правообладателем могут возникнуть недопонимания и разногласия.");
            consultationsAnswer2.setNote("ОБРАТИТЕ ВНИМАНИЕ! Рекомендуем Вам дополнительно согласовать эти вопросы с Правообладателем.");
            consultationsAnswer2.setQuestion(consultationsQuestion);

            // Ответ 9.3
            Answer consultationsAnswer3 = new Answer();
            consultationsAnswer3.setText("Такое условие не прописано");
            consultationsAnswer3.setRisk("Высокий");
            consultationsAnswer3.setExplanation("Гражданским Кодексом в ст.1031 РФ закреплена обязанность Правообладателя обеспечить проведение консультаций. Отсутствие надлежащим образом оформленных консультаций влечет риск возникновения организационных и финансовых трудностей при ведении бизнеса.");
            consultationsAnswer3.setNote("ОБРАТИТЕ ВНИМАНИЕ! Рекомендуем Вам согласовать с Правообладателем это условие.");
            consultationsAnswer3.setQuestion(consultationsQuestion);

            consultationsAnswers.add(consultationsAnswer1);
            consultationsAnswers.add(consultationsAnswer2);
            consultationsAnswers.add(consultationsAnswer3);
            consultationsQuestion.setAnswers(consultationsAnswers);
            questionRepository.save(consultationsQuestion);

            // Категория 10: Проверка деятельности
            Question inspectionQuestion = new Question();
            inspectionQuestion.setTitle("ПРОВЕРКА ДЕЯТЕЛЬНОСТИ");
            inspectionQuestion.setQuestionText("Указан ли в Вашем договоре порядок проведения Правообладателем проверок деятельности Пользователя?");

            List<Answer> inspectionAnswers = new ArrayList<>();

            // Ответ 10.1
            Answer inspectionAnswer1 = new Answer();
            inspectionAnswer1.setText("Правообладатель вправе проводить проверки в любое время по любым аспектам деятельности");
            inspectionAnswer1.setRisk("Высокий");
            inspectionAnswer1.setExplanation("Если Правообладатель имеет право проверить деятельность Вашего предприятия в любое время, по любым направлениям и в любом объеме, это может привести к приостановке работы и финансовым потерям.");
            inspectionAnswer1.setNote("ОБРАТИТЕ ВНИМАНИЕ! Рекомендуем согласовать ограничения на частоту и условия проверок.");
            inspectionAnswer1.setQuestion(inspectionQuestion);

            // Ответ 10.2
            Answer inspectionAnswer2 = new Answer();
            inspectionAnswer2.setText("Проверки возможны только в рабочее время и не должны мешать хозяйственной деятельности");
            inspectionAnswer2.setRisk("Низкий");
            inspectionAnswer2.setExplanation("Правообладатель не может проводить проверки произвольно, что снижает риски для Вашего бизнеса.");
            inspectionAnswer2.setQuestion(inspectionQuestion);

            // Ответ 10.3
            Answer inspectionAnswer3 = new Answer();
            inspectionAnswer3.setText("Условие о проверках не прописано");
            inspectionAnswer3.setRisk("Средний");
            inspectionAnswer3.setExplanation("Отсутствие регламентации порядка проверок создает риск возникновения разногласий с Правообладателем.");
            inspectionAnswer3.setQuestion(inspectionQuestion);

            inspectionAnswers.add(inspectionAnswer1);
            inspectionAnswers.add(inspectionAnswer2);
            inspectionAnswers.add(inspectionAnswer3);
            inspectionQuestion.setAnswers(inspectionAnswers);
            questionRepository.save(inspectionQuestion);

            // Категория 11: Срок действия договора
            Question durationQuestion = new Question();
            durationQuestion.setTitle("СРОК ДЕЙСТВИЯ ДОГОВОРА");
            durationQuestion.setQuestionText("Установлен ли в Вашем договоре срок его действия?");

            List<Answer> durationAnswers = new ArrayList<>();

            // Ответ 11.1
            Answer durationAnswer1 = new Answer();
            durationAnswer1.setText("Да, срок действия договора до 2 лет и менее");
            durationAnswer1.setRisk("Высокий");
            durationAnswer1.setExplanation("Риск экономических потерь, а также риск купить франшизу, которая не приносит реальной прибыли (как правило такой срок характерен для франшиз-пустышек).");
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
            durationAnswer3.setExplanation("Как правило, условие о таком сроке будет наилучшим для франчайзи, так как за этот период наиболее вероятна успешная окупаемость и максимальная прибыль.");
            durationAnswer3.setQuestion(durationQuestion);

            // Ответ 11.4
            Answer durationAnswer4 = new Answer();
            durationAnswer4.setText("Нет, срок не указан (бессрочный договор)");
            durationAnswer4.setRisk("Высокий");
            durationAnswer4.setExplanation("Договор лучше заключать на определенный срок, чтобы сохранить гарантию преимущественного права заключить договор на новый срок.");
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

            List<Answer> renewalAnswers = new ArrayList<>();

            // Ответ 12.1
            Answer renewalAnswer1 = new Answer();
            renewalAnswer1.setText("Пользователь имеет право на заключение договора на новый срок при отсутствии нарушений");
            renewalAnswer1.setRisk("Низкий");
            renewalAnswer1.setExplanation("В таком случае меньше вероятность потерять вложенные в бизнес деньги и появляется возможность продолжать работать под брендом.");
            renewalAnswer1.setQuestion(renewalQuestion);

            // Ответ 12.2
            Answer renewalAnswer2 = new Answer();
            renewalAnswer2.setText("Правообладатель вправе отказать в заключении договора на новый срок");
            renewalAnswer2.setRisk("Высокий");
            renewalAnswer2.setExplanation("По истечении срока действия договора Вы рискуете полностью потерять бизнес, так как не сможете пользоваться товарным знаком и другими переданными правами.");
            renewalAnswer2.setQuestion(renewalQuestion);

            renewalAnswers.add(renewalAnswer1);
            renewalAnswers.add(renewalAnswer2);
            renewalQuestion.setAnswers(renewalAnswers);
            questionRepository.save(renewalQuestion);

            // Категория 13: Судьба оборудования при прекращении договора
            Question equipmentFateQuestion = new Question();
            equipmentFateQuestion.setTitle("СУДЬБА ОБОРУДОВАНИЯ ПРИ ПРЕКРАЩЕНИИ ДОГОВОРА");
            equipmentFateQuestion.setQuestionText("Как в Вашем договоре определяется судьба оборудования при прекращении договора?");

            List<Answer> equipmentFateAnswers = new ArrayList<>();

            // Ответ 13.1
            Answer equipmentFateAnswer1 = new Answer();
            equipmentFateAnswer1.setText("Правообладатель имеет право выкупить оборудование по остаточной стоимости");
            equipmentFateAnswer1.setRisk("Средний");
            equipmentFateAnswer1.setExplanation("Риск заключается в том, что если Правообладатель не проявит интерес к выкупу, франчайзи может остаться с нереализованным оборудованием.");
            equipmentFateAnswer1.setQuestion(equipmentFateQuestion);

            // Ответ 13.2
            Answer equipmentFateAnswer2 = new Answer();
            equipmentFateAnswer2.setText("Пользователь вправе реализовать оборудование после удаления знаков правообладателя");
            equipmentFateAnswer2.setRisk("Низкий");
            equipmentFateAnswer2.setExplanation("Возможность реализации оборудования снижает риск потерь, однако требует соблюдения условий по удалению брендинга.");
            equipmentFateAnswer2.setQuestion(equipmentFateQuestion);

            // Ответ 13.3
            Answer equipmentFateAnswer3 = new Answer();
            equipmentFateAnswer3.setText("Обязанность вернуть оборудование Правообладателю за свой счет");
            equipmentFateAnswer3.setRisk("Высокий");
            equipmentFateAnswer3.setExplanation("Франчайзи рискует понести значительные расходы на возврат оборудования.");
            equipmentFateAnswer3.setQuestion(equipmentFateQuestion);

            equipmentFateAnswers.add(equipmentFateAnswer1);
            equipmentFateAnswers.add(equipmentFateAnswer2);
            equipmentFateAnswers.add(equipmentFateAnswer3);
            equipmentFateQuestion.setAnswers(equipmentFateAnswers);
            questionRepository.save(equipmentFateQuestion);

            // Категория 14: Конфиденциальность
            Question confidentialityQuestion = new Question();
            confidentialityQuestion.setTitle("КОНФИДЕНЦИАЛЬНОСТЬ");
            confidentialityQuestion.setQuestionText("Как в Вашем договоре регламентируется обязанность по защите конфиденциальных данных?");

            List<Answer> confidentialityAnswers = new ArrayList<>();

            // Ответ 14.1
            Answer confidentialityAnswer1 = new Answer();
            confidentialityAnswer1.setText("Обязательства действуют в течение срока договора и несколько лет после");
            confidentialityAnswer1.setRisk("Средний");
            confidentialityAnswer1.setExplanation("Вы не сможете использовать или разглашать полученные данные в течение длительного времени.");
            confidentialityAnswer1.setQuestion(confidentialityQuestion);

            // Ответ 14.2
            Answer confidentialityAnswer2 = new Answer();
            confidentialityAnswer2.setText("Пользователь обязан принимать все меры для защиты информации");
            confidentialityAnswer2.setRisk("Высокий");
            confidentialityAnswer2.setExplanation("Обеспечение защиты данных может быть затратным, а в случае утечки вы будете нести ответственность.");
            confidentialityAnswer2.setQuestion(confidentialityQuestion);

            confidentialityAnswers.add(confidentialityAnswer1);
            confidentialityAnswers.add(confidentialityAnswer2);
            confidentialityQuestion.setAnswers(confidentialityAnswers);
            questionRepository.save(confidentialityQuestion);

            // Категория 15: Гарантии успешности бизнеса
            Question guaranteesQuestion = new Question();
            guaranteesQuestion.setTitle("ГАРАНТИИ УСПЕШНОСТИ БИЗНЕСА");
            guaranteesQuestion.setQuestionText("Содержится ли в договоре условие об отсутствии гарантий успешности бизнеса?");

            List<Answer> guaranteesAnswers = new ArrayList<>();

            // Ответ 15.1
            Answer guaranteesAnswer1 = new Answer();
            guaranteesAnswer1.setText("Да, прямо указано об отсутствии гарантий");
            guaranteesAnswer1.setRisk("Высокий");
            guaranteesAnswer1.setExplanation("Правообладатель специально подчеркивает, что не дает гарантий в отношении возможных результатов работы.");
            guaranteesAnswer1.setQuestion(guaranteesQuestion);

            // Ответ 15.2
            Answer guaranteesAnswer2 = new Answer();
            guaranteesAnswer2.setText("Нет, такое условие отсутствует");
            guaranteesAnswer2.setRisk("Средний");
            guaranteesAnswer2.setExplanation("Отсутствие подобных условий лучше, чем их наличие, однако это не является гарантией успешности бизнеса.");
            guaranteesAnswer2.setQuestion(guaranteesQuestion);

            guaranteesAnswers.add(guaranteesAnswer1);
            guaranteesAnswers.add(guaranteesAnswer2);
            guaranteesQuestion.setAnswers(guaranteesAnswers);
            questionRepository.save(guaranteesQuestion);

            // Категория 16: Подсудность
            Question jurisdictionQuestion = new Question();
            jurisdictionQuestion.setTitle("ПОДСУДНОСТЬ");
            jurisdictionQuestion.setQuestionText("Какой суд для разрешения споров указан в Вашем договоре?");

            List<Answer> jurisdictionAnswers = new ArrayList<>();

            // Ответ 16.1
            Answer jurisdictionAnswer1 = new Answer();
            jurisdictionAnswer1.setText("Арбитражный суд по месту нахождения Пользователя");
            jurisdictionAnswer1.setRisk("Низкий");
            jurisdictionAnswer1.setExplanation("Вам будет удобнее и менее затратно разрешать споры в суде по своему местонахождению.");
            jurisdictionAnswer1.setQuestion(jurisdictionQuestion);

            // Ответ 16.2
            Answer jurisdictionAnswer2 = new Answer();
            jurisdictionAnswer2.setText("Арбитражный суд по месту нахождения Правообладателя");
            jurisdictionAnswer2.setRisk("Средний");
            jurisdictionAnswer2.setExplanation("Суд по месту нахождения Правообладателя может находиться далеко, что повлечет дополнительные расходы.");
            jurisdictionAnswer2.setQuestion(jurisdictionQuestion);

            jurisdictionAnswers.add(jurisdictionAnswer1);
            jurisdictionAnswers.add(jurisdictionAnswer2);
            jurisdictionQuestion.setAnswers(jurisdictionAnswers);
            questionRepository.save(jurisdictionQuestion);

            log.info("Данные успешно загружены в базу данных");
        };
    }
}
