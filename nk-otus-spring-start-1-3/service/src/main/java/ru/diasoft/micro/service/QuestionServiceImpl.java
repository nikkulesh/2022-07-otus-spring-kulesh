package ru.diasoft.micro.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.diasoft.micro.dao.QuestionDao;
import ru.diasoft.micro.dao.QuestionDaoImpl;
import ru.diasoft.micro.domain.QuestionAnswer;

import java.util.List;
import java.util.Scanner;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Value("${test.passing-score}")
    private int passingScore = 4;

    private final String file;
    private final QuestionDao questionDao;

    public QuestionServiceImpl(String file, QuestionDao questionDao) {
        this.file = file;
        this.questionDao = questionDao;
    }

    public QuestionServiceImpl() {
        this.file = "/questions.csv";
        this.questionDao = new QuestionDaoImpl();
    }

    /**
     * Метод спрашивает имя и фамилию, задает проводит тест по вопросам из CSV-файла и выводит результат.
     */
    @Override
    public void showQuestions() {
        List<QuestionAnswer> list = questionDao.readQuestions(file);
        String surname = "";
        String name = "";
        int score = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name:");
        if (in.hasNextLine()) {
            name = in.nextLine();
        }
        System.out.println("Enter your surname:");
        if (in.hasNextLine()) {
            surname = in.nextLine();
        }
        if (name.isEmpty() || surname.isEmpty()){
            System.out.println("Credentials are not valid.");
            return;
        }
        for (QuestionAnswer question : list) {
            String actualAnswer;
            System.out.println("Question: " + question.getQuestion() + " Enter your answer:");
            if (in.hasNextLine()) {
                actualAnswer = in.nextLine();
                if (actualAnswer.equals(question.getAnswer())){
                    score++;
                }
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
