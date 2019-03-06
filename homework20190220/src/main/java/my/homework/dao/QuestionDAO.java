package my.homework.dao;

import my.homework.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionDAO {
    List<Question> getQuestions(Locale locale);
}
