package ru.diasoft.micro.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.dao.QuestionDao;
import ru.diasoft.micro.dao.ReaderDao;
import ru.diasoft.micro.domain.QuestionAnswer;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @Value("${test.passing-score}")
    private final int passingScore = 4;

    private final String file = "/questions.csv";
    private QuestionDao questionDao;
    private ReaderDao readerDao;

    /**
     * Метод спрашивает имя и фамилию, задает проводит тест по вопросам из CSV-файла и выводит результат.
     */
    @Override
    public void showQuestions() {
        List<QuestionAnswer> list = questionDao.readQuestions(file);
        String surname = "";
        String name = "";
        int score = 0;
        System.out.println("Enter your name:");
        name = readerDao.readFromConsole("Enter your name:");
        System.out.println("Enter your surname:");
        surname = readerDao.readFromConsole("Enter your surname:");
        if (name.isEmpty() || surname.isEmpty()){
            System.out.println("Credentials are not valid.");
            return;
        }
        for (QuestionAnswer question : list) {
            String actualAnswer;
            System.out.println("Question: " + question.getQuestion() + " Enter your answer:");
            actualAnswer = readerDao.readFromConsole("Question: " + question.getQuestion() + " Enter your answer:");
            if (actualAnswer.equals(question.getAnswer())){
                score++;
            }
        }

        if (score >= passingScore){
            System.out.println("Congratulations! User " + name + " " + surname + " have passed the test!");
        }
        else {
            System.out.println("User " + name + " " + surname + " have failed this test.");
        }
    }
}
