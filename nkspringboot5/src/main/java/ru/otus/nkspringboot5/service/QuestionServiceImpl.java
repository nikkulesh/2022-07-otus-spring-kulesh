package ru.otus.nkspringboot5.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.nkspringboot5.dao.ConsoleDao;
import ru.otus.nkspringboot5.dao.QuestionDao;
import ru.otus.nkspringboot5.domain.QuestionAnswer;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Value("${test.passing-score}")
    private final int passingScore = 4;

    @Value("${test.file-name}")
    private final String file = "/questions";
    private final QuestionDao questionDao;
    private final ConsoleDao consoleDao;
    private final MessageSource messageSource;

    /**
     * Метод спрашивает имя и фамилию, проводит тест по вопросам из CSV-файла и выводит результат.
     */
    @Override
    public boolean showQuestions() {
        List<QuestionAnswer> list = questionDao.readQuestions(file + "_" + Locale.getDefault() + ".csv");
        String surname = "";
        String name = "";
        int score = 0;
        consoleDao.printIntoConsole(messageSource.getMessage("question_service.enter_name", null, Locale.getDefault()));
        name = consoleDao.readFromConsole();
        consoleDao.printIntoConsole(messageSource.getMessage("question_service.enter_surname", null, Locale.getDefault()));
        surname = consoleDao.readFromConsole();
        if (name.isEmpty() || surname.isEmpty()){
            consoleDao.printIntoConsole(messageSource.getMessage("question_service.error_credentials", null, Locale.getDefault()));
            return false;
        }
        for (QuestionAnswer question : list) {
            String actualAnswer;
            consoleDao.printIntoConsole(messageSource.getMessage("question_service.ask_question", new Object[] {question.getQuestion()}, Locale.getDefault()));
            actualAnswer = consoleDao.readFromConsole();
            if (actualAnswer.equals(question.getAnswer())){
                score++;
            }
        }

        if (score >= passingScore){
            consoleDao.printIntoConsole(messageSource.getMessage("question_service.test_success", new Object[] {name, surname}, Locale.getDefault()));
            return true;
        }
        else {
            consoleDao.printIntoConsole(messageSource.getMessage("question_service.test_fail", new Object[] {name, surname}, Locale.getDefault()));
            return false;
        }
    }
}
