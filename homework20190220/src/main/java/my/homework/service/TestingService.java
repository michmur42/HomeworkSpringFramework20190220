package my.homework.service;

import my.homework.domain.Answer;

import java.util.List;

public interface TestingService {
    /**
     * Метод начинает процесс тестирование
     */
    List<Answer> start();

    /**
     * Метод осущевствлят проверку результатов тестирования
     *
     * @param history История тестирования
     */
    void validate(List<Answer> history);
}
