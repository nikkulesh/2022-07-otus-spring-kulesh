package ru.otus.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.ConsoleDao;
import ru.otus.domain.QuestionAnswer;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Value("${test.passing-score}")
    private final int passingScore = 4;

    @Value("${test.file-name}")
    private final String file = "/questions.csv";
    private QuestionDao questionDao;
    private ConsoleDao consoleDao;

    /**
     * Метод спрашивает имя и фамилию, проводит тест по вопросам из CSV-файла и выводит результат.
     */
    @Override
    public boolean showQuestions() {
        List<QuestionAnswer> list = questionDao.readQuestions(file);
        String surname = "";
        String name = "";
        int score = 0;
        consoleDao.printIntoConsole("Enter your name:");
        name = consoleDao.readFromConsole();
        consoleDao.printIntoConsole("Enter your surname:");
        surname = consoleDao.readFromConsole();
        if (name.isEmpty() || surname.isEmpty()){
            consoleDao.printIntoConsole("Credentials are not valid.");
            return false;
        }
        for (QuestionAnswer question : list) {
            String actualAnswer;
            consoleDao.printIntoConsole("Question: " + question.getQuestion() + " Enter your answer:");
            actualAnswer = consoleDao.readFromConsole();
            if (actualAnswer.equals(question.getAnswer())){
                score++;
            }
        }

        if (score >= passingScore){
            consoleDao.printIntoConsole("Congratulations! User " + name + " " + surname + " have passed the test!");
            return true;
        }
        else {
            consoleDao.printIntoConsole("User " + name + " " + surname + " have failed this test.");
            return false;
        }
    }
}
