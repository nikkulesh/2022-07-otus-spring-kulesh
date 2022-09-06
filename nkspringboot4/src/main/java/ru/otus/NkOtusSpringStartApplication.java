package ru.otus;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.service.QuestionService;
import ru.otus.service.QuestionServiceImpl;

@SpringBootApplication(scanBasePackages = "ru.otus")
public class NkOtusSpringStartApplication {

    public static void main(String[] args) {

        QuestionService service = new QuestionServiceImpl();
        service.showQuestions();
    }

}
