package ru.otus.nkspringboot5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.nkspringboot5.dao.ConsoleDao;
import ru.otus.nkspringboot5.dao.ConsoleDaoImpl;
import ru.otus.nkspringboot5.dao.QuestionDao;
import ru.otus.nkspringboot5.dao.QuestionDaoImpl;
import ru.otus.nkspringboot5.service.QuestionService;
import ru.otus.nkspringboot5.service.QuestionServiceImpl;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "ru.otus.nkspringboot5")
public class Nkspringboot5Application {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Nkspringboot5Application.class, args);

		Scanner in = new Scanner(System.in);
		QuestionDao questionDao = new QuestionDaoImpl();
		ConsoleDao consoleDao = new ConsoleDaoImpl(in);
		MessageSource messageSource = ctx.getBean(MessageSource.class);

		QuestionService questionService = new QuestionServiceImpl(questionDao, consoleDao, messageSource);
		questionService.showQuestions();
	}

}
