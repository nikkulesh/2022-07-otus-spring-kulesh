package ru.otus.nkspringboot5.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.nkspringboot5.dao.ConsoleDao;
import ru.otus.nkspringboot5.dao.QuestionDao;
import ru.otus.nkspringboot5.domain.QuestionAnswer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис по чтению вопросов из scv-файла")
class QuestionServiceImplTest {

    private ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    private QuestionServiceImpl service;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private ConsoleDao consoleDao;
    @Mock
    private MessageSource source;

    @BeforeEach
    public void init() {
        service = new QuestionServiceImpl(questionDao, consoleDao, source);
        System.setOut(new PrintStream(consoleOut));
    }

    @Test
    @DisplayName("сервис должен выводить вопросы в консоль при верном вводе")
    void QuestionServicePrintCheck() {
        List<QuestionAnswer> list = new ArrayList<>();
        QuestionAnswer question1 = QuestionAnswer.builder()
                .question("What's 1+1?")
                .answer("2")
                .build();
        QuestionAnswer question2 = QuestionAnswer.builder()
                .question("What's 1+1?")
                .answer("2")
                .build();
        QuestionAnswer question3 = QuestionAnswer.builder()
                .question("What's 1+1?")
                .answer("2")
                .build();
        QuestionAnswer question4 = QuestionAnswer.builder()
                .question("What's 1+1?")
                .answer("2")
                .build();
        QuestionAnswer question5 = QuestionAnswer.builder()
                .question("What's 1+1?")
                .answer("2")
                .build();
        list.add(question1);
        list.add(question2);
        list.add(question3);
        list.add(question4);
        list.add(question5);
        when(questionDao.readQuestions("/questions_ru_RU.csv"))
                .thenReturn(list);

        when(consoleDao.readFromConsole())
                .thenReturn("2");
        Assertions.assertTrue(service.showQuestions());
    }

    @Test
    @DisplayName("сервис должен заканчивать работу при пустом имени или фамилии")
    void QuestionServicePrintEmptyCheck() {
        List<QuestionAnswer> list = new ArrayList<>();
        QuestionAnswer question = QuestionAnswer.builder()
                .question("What's 1+1?")
                .answer("2")
                .build();
        list.add(question);
        when(questionDao.readQuestions("/questions_ru_RU.csv"))
                .thenReturn(list);

        when(consoleDao.readFromConsole())
                .thenReturn("");
        Assertions.assertFalse(service.showQuestions());
    }

}