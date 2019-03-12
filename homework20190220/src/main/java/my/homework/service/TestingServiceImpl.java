package my.homework.service;

import my.homework.dao.QuestionDAO;
import my.homework.domain.Answer;
import my.homework.domain.Question;
import my.homework.exception.StopException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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

  public List<Answer> start() throws IOException, StopException {
    this.questions = questionDAO.getQuestions(locale);
    Assert.notEmpty(questions, messageService.getMessage("message.error.data"));
    String userName = inputService.waitInput(messageService.getMessage("prompt.user"));
    List<Answer> history = new ArrayList<>();
    for (Question question : questions) {
      history.add(questionService.doAsk(question));
    }
    return history;
  }

  public void validate(List<Answer> history) {
    long success = history.stream().filter(answer -> answer.getChoice().getFlag()).count();
    long fail = history.stream().filter(answer -> !answer.getChoice().getFlag()).count();
    System.out.println();
    System.out.println(MessageFormat.format(messageService.getMessage("prompt.success"), success));
    System.out.println(MessageFormat.format(messageService.getMessage("prompt.fail"), fail));
  }
}
