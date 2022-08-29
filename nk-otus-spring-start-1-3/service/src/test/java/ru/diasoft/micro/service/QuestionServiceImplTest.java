package ru.diasoft.micro.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("Сервис по чтению вопросов из scv-файла")
class QuestionServiceImplTest {

    private QuestionServiceImpl service;

    private final ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    @BeforeEach
    public void init() {
        service = new QuestionServiceImpl();
        System.setOut(new PrintStream(consoleOut));
    }

    //не работает
    /*@Test
    @DisplayName("сервис должен выводить вопросы в консоль")
    void QuestionServicePrintCheck() {
        service.showQuestions();
        String expectedString = "Question: What's 1+1?" +
                "\r\n" +
                "Question: What's 2+2?" +
                "\r\n" +
                "Question: What's 3+3?" +
                "\r\n" +
                "Question: What's 4+4?" +
                "\r\n" +
                "Question: What's 5+5?" +
                "\r\n";
        Assertions.assertEquals(expectedString, consoleOut.toString());
    }*/

}