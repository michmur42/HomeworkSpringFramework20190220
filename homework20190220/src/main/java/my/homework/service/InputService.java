package my.homework.service;

import java.io.IOException;

public interface InputService {
  /**
   * Ввод данных пользователеи
   *
   * @param prompt приглашение для пользователя
   * @return результат ввода
   */
  String waitInput(String prompt) throws IOException;

  /**
   * Ввод данных пользователеи
   *
   * @return результат ввода
   */
  String waitInput() throws IOException;

}
