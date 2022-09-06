package ru.otus.dao;

import ru.otus.domain.QuestionAnswer;

import java.util.List;

public interface QuestionDao {

    List<QuestionAnswer> readQuestions(String file);
}
