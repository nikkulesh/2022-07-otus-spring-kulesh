package ru.diasoft.micro;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.Generated;
import ru.diasoft.micro.service.QuestionService;
import ru.diasoft.micro.service.QuestionServiceImpl;

@SpringBootApplication(scanBasePackages = "ru.diasoft.micro")
@Generated
public class NkOtusSpringStartApplication {

    public static void main(String[] args) {

        QuestionService service = new QuestionServiceImpl();
        service.showQuestions();
    }

}
