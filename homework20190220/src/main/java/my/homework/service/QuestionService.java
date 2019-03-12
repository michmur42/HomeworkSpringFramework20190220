package my.homework.service;

import my.homework.domain.Answer;
import my.homework.domain.Question;
import my.homework.exception.StopException;

import java.io.IOException;

public interface QuestionService {
  /**
   * Обработка выбора ответа пользователем
   *
   * @param question вопрос
   * @return ответ
   * @throws IOException   Остановка ввиду исключительной ситуации
   * @throws StopException Остановка тестирования пользователем
   */
  Answer doAsk(Question question) throws IOException, StopException;
}
