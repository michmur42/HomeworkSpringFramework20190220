package my.homework.dao;

import my.homework.domain.Option;
import my.homework.domain.Question;
import my.homework.service.MessageServiceImpl;
import my.homework.util.CSVHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Данный класс реализует интерфейс QuestionDAO и предоставляет доступ к списку вопросов
 * и ответов из файлов questions.csv и options.csv находящихся в classpath
 */
@Component
public class FileQuestionsDAOImpl implements QuestionDAO {

  /**
   * Файл с вопросами
   */
  private Resource questionResource = new ClassPathResource("questions.csv");

  /**
   * Файл с ответами
   */
  private Resource answerResource = new ClassPathResource("options.csv");

  /**
   * Сервис для работы с message bundle
   */
  private MessageServiceImpl messageService;

  public FileQuestionsDAOImpl(MessageServiceImpl messageService) {
    this.messageService = messageService;
  }

  /**
   * Определение ресурса с вопросами
   * @param questionResource файл с вопросами
   */
  public void setQuestionResource(Resource questionResource) {
    this.questionResource = questionResource;
  }

  /**
   * Определение ресурса с ответами
   * @param answerResource файл с ответами
   */
  public void setAnswerResource(Resource answerResource) {
    this.answerResource = answerResource;
  }

  /**
   * Возвращает список с вопросами и ответами
   *
   * @param locale локализация
   * @return список вопросов
   */
  public List<Question> getQuestions(Locale locale) {
    try {
      List<Option> options = CSVHelper.readFromCSV(answerResource, Option.class);
      return CSVHelper.readFromCSV(questionResource, Question.class).stream()
              .filter(q -> locale.getLanguage().equals(q.getLanguage()))
              .peek(q -> q.setOptions(options.stream()
                      .filter(a -> locale.getLanguage().equals(locale.getLanguage()))
                      .filter(a -> a.getQuestionId().equals(q.getId()))
                      .collect(Collectors.toList())))
              .collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(messageService.getMessage("message.error.parse"), e);
    }
  }
}
