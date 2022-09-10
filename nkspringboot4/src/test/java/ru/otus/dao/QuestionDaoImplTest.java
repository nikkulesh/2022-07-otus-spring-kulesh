package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.domain.QuestionAnswer;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayName("dao по чтению вопросов из scv-файла")
class QuestionDaoImplTest {

    private QuestionDaoImpl dao;

    @BeforeEach
    public void init() {
        dao = new QuestionDaoImpl();
    }

    @Test
    @DisplayName("dao должен возвращать список из файла")
    void QuestionDaoFileReadCheck() {
        List<QuestionAnswer> actualList = dao.readQuestions("/questions.csv");
        QuestionAnswer question1 = QuestionAnswer.builder().question("What's 1+1?").answer("2").build();
        QuestionAnswer question2 = QuestionAnswer.builder().question("What's 2+2?").answer("4").build();
        QuestionAnswer question3 = QuestionAnswer.builder().question("What's 3+3?").answer("6").build();
        QuestionAnswer question4 = QuestionAnswer.builder().question("What's 4+4?").answer("8").build();
        QuestionAnswer question5 = QuestionAnswer.builder().question("What's 5+5?").answer("10").build();
        List<QuestionAnswer> expectedList = new ArrayList<>();
        expectedList.add(question1);
        expectedList.add(question2);
        expectedList.add(question3);
        expectedList.add(question4);
        expectedList.add(question5);

        assertThat(actualList).isEqualTo(expectedList);
    }

}