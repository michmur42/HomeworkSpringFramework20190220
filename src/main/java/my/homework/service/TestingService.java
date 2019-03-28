package my.homework.service;

import my.homework.domain.Result;
import my.homework.exception.StopException;

import java.io.IOException;

public interface TestingService {
  /**
   * Метод начинает процесс тестирование
   */
  Result start() throws IOException, StopException;

}
