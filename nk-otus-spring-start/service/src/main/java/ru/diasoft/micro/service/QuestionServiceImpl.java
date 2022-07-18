package ru.diasoft.micro.service;

import org.springframework.stereotype.Service;
import ru.diasoft.micro.dao.QuestionDao;
import ru.diasoft.micro.dao.QuestionDaoImpl;
import ru.diasoft.micro.domain.QuestionAnswer;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

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
     * Метод выводит вопросы из CSV-файла.
     */
    @Override
    public void showQuestions() {

        List<QuestionAnswer> list = questionDao.readQuestions(file);
        for (QuestionAnswer question : list) {
            System.out.println("Question: " + question.getQuestion());
        }
    }
}
