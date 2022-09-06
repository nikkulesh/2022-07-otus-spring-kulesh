package ru.otus.dao;

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
@DisplayName("dao по чтению/записи в консоль")
class ConsoleDaoImplTest {

    private final ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    private ConsoleDaoImpl dao;

    @BeforeEach
    public void init() {
        dao = new ConsoleDaoImpl();
        System.setOut(new PrintStream(consoleOut));
    }

    @Test
    @DisplayName("dao должен писать в консоль")
    void ConsoleDaoPrintCheck() {

        String expectedString = "Enter your name:" +
                "\r\n" +
                "Enter your surname:" +
                "\r\n" +
                "Question: What's 1+1? Enter your answer:" +
                "\r\n" +
                "Question: What's 2+2? Enter your answer:" +
                "\r\n" +
                "Question: What's 3+3? Enter your answer:" +
                "\r\n" ;

        dao.printIntoConsole("Enter your name:");
        dao.printIntoConsole("Enter your surname:");
        dao.printIntoConsole("Question: What's 1+1? Enter your answer:");
        dao.printIntoConsole("Question: What's 2+2? Enter your answer:");
        dao.printIntoConsole("Question: What's 3+3? Enter your answer:");

        Assertions.assertEquals(expectedString, consoleOut.toString());
    }

}