package my.homework.service;

import my.homework.dao.QuestionDAO;
import my.homework.domain.Question;
import my.homework.domain.Result;
import my.homework.exception.StopException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class TestingServiceImpl implements TestingService {

  private final QuestionService questionService;
  private final QuestionDAO questionDAO;
  private final InputService inputService;
  private Locale locale;
  private List<Question> questions;
  private MessageService messageService;


  public TestingServiceImpl(@Value("${application.language}") String language,
                            QuestionDAO questionDAO,
                            QuestionService questionService,
                            MessageService messageService,
                            InputService inputService) {
    this.questionDAO = questionDAO;
    this.messageService = messageService;
    this.locale = new Locale(language);
    this.questionService = questionService;
    this.inputService = inputService;
  }

  public Result start() throws IOException, StopException {
    this.questions = questionDAO.getQuestions(locale);
    Assert.notEmpty(questions, messageService.getMessage("message.error.data"));
    Result result = new Result();
    result.setUser(inputService.waitInput(messageService.getMessage("prompt.user")));
    try {
      for (Question question : questions) {
        result.getAnswers().add(questionService.doAsk(question));
      }
      System.out.println(messageService.getMessage("message.info.end"));
    } catch (StopException e) {
      System.out.println(messageService.getMessage("message.error.stop"));
    }
    return result;
  }
}
