package ru.otus;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.service.QuestionService;
import ru.otus.service.QuestionServiceImpl;

import lombok.Generated;

@SpringBootApplication(scanBasePackages = "ru.otus")
@Generated
public class NkOtusSpringStartApplication {

    public static void main(String[] args) {

        QuestionService service = new QuestionServiceImpl();
        service.showQuestions();
    }

}
