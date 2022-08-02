package ru.diasoft.micro.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.diasoft.micro.dao.QuestionDao;
import ru.diasoft.micro.dao.ReaderDao;
import ru.diasoft.micro.domain.QuestionAnswer;

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
    private ReaderDao readerDao;

    @BeforeEach
    public void init() {
        service = new QuestionServiceImpl(questionDao,readerDao);
        System.setOut(new PrintStream(consoleOut));
    }

    @Test
    @DisplayName("сервис должен выводить вопросы в консоль")
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
        when(questionDao.readQuestions("/questions.csv"))
                .thenReturn(list);

        when(readerDao.readFromConsole("Enter your name:"))
                .thenReturn("testName");
        when(readerDao.readFromConsole("Enter your surname:"))
                .thenReturn("testSurname");
        when(readerDao.readFromConsole("Question: What's 1+1? Enter your answer:"))
                .thenReturn("2");
        service.showQuestions();
        String expectedString = "Enter your name:" +
                "\r\n" +
                "Enter your surname:" +
                "\r\n" +
                "Question: What's 1+1? Enter your answer:" +
                "\r\n" +
                "Question: What's 1+1? Enter your answer:" +
                "\r\n" +
                "Question: What's 1+1? Enter your answer:" +
                "\r\n" +
                "Question: What's 1+1? Enter your answer:" +
                "\r\n" +
                "Question: What's 1+1? Enter your answer:" +
                "\r\n" +
                "Congratulations! User testName testSurname have passed the test!" +
                "\r\n";

        Assertions.assertEquals(expectedString, consoleOut.toString());
    }

}