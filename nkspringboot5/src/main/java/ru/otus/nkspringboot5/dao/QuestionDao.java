package ru.otus.nkspringboot5.dao;

import ru.otus.nkspringboot5.domain.QuestionAnswer;

import java.util.List;

public interface QuestionDao {

    List<QuestionAnswer> readQuestions(String file);
}
