package my.homework.dao;

import my.homework.domain.Question;

import java.util.List;
import java.util.Locale;

public interface QuestionDAO {
  /**
   * Возвращает локализированный список вопросов с вариантами ответа
   *
   * @param locale локализация
   * @return Список ответов
   */
  List<Question> getQuestions(Locale locale);
}
