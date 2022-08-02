package ru.diasoft.micro.dao;

import org.springframework.stereotype.Service;
import ru.diasoft.micro.domain.QuestionAnswer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionDaoImpl implements QuestionDao{

    /**
     * Метод для считывания вопросов-ответов из файла.
     *
     * @param file название файла ресурса для чтения.
     */
    @Override
    public List<QuestionAnswer> readQuestions(String file){
        List<QuestionAnswer> resultList = new ArrayList<>();

        InputStream in = getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        Scanner scanner;

        try {
            while ((line = reader.readLine()) != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                QuestionAnswer question = new QuestionAnswer();
                if (scanner.hasNext()) {
                    question.setQuestion(scanner.next());
                    if (scanner.hasNext()) {
                        question.setAnswer(scanner.next());
                        resultList.add(question);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        return resultList;
    }
}
