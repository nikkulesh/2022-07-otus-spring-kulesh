package ru.diasoft.micro;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.Generated;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.diasoft.micro.service.QuestionService;

@SpringBootApplication(scanBasePackages = "ru.diasoft.micro")
@Generated
public class NkOtusSpringStartApplication {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        service.showQuestions();
    }

}
