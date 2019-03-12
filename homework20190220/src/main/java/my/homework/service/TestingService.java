package my.homework.service;

import my.homework.domain.Answer;
import my.homework.exception.StopException;

import java.io.IOException;
import java.util.List;

public interface TestingService {
  /**
   * Метод начинает процесс тестирование
   */
  List<Answer> start() throws IOException, StopException;

  /**
   * Метод осущевствлят проверку результатов тестирования
   *
   * @param history История тестирования
   */
  void validate(List<Answer> history);
}
