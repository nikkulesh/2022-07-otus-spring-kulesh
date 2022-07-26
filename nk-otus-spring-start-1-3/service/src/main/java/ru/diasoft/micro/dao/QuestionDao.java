package ru.diasoft.micro.dao;

import ru.diasoft.micro.domain.QuestionAnswer;

import java.util.List;

public interface QuestionDao {

    List<QuestionAnswer> readQuestions(String file);
}
