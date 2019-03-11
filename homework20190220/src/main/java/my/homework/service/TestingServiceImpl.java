package my.homework.service;

import my.homework.dao.QuestionDAO;
import my.homework.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestingServiceImpl implements TestingService {


  private final QuestionDAO questionDAO;
  @Value("${application.language}")
  private String language;
  private Locale locale;
  private List<Question> questions;
  private MessageService messageService;

  public TestingServiceImpl(QuestionDAO questionDAO, MessageService messageService) {
    this.questionDAO = questionDAO;
    this.messageService = messageService;
    this.locale = new Locale(language);
  }

  public void start() {
    this.questions = questionDAO.getQuestions(locale);
    Assert.notEmpty(questions, messageService.getMessage("error.data.notfound"));
    int num = 0;
    int succsess = 0;
    int fail = 0;
    System.out.print(messageService.getMessage("test.user.enter"));
    String personInfo = new Scanner(System.in).next();
    for (Question question : questions) {
      num++;
      StringBuilder sb = new StringBuilder();
      System.out.println("-------------------------------------------------------------------------------------");
      System.out.println(MessageFormat.format(messageService.getMessage("test.question.label"), num));
      System.out.println(question.getText());
      System.out.println(messageService.getMessage("test.answers.label"));
      AtomicInteger incr = new AtomicInteger(0);
      question.getAnswers().stream().forEach(a -> {
        incr.incrementAndGet();
        System.out.println(MessageFormat.format("{0} - {1}", incr.get(), a.getText()));
      });
      System.out.println(messageService.getMessage("test.answer.choice"));
      Scanner scanner = new Scanner(System.in);
      boolean checker = true;
      while (checker) {
        String inputValue = scanner.next();
        if ("q".equals(inputValue)) {
          return;
        }
        try {
          Integer n = Integer.parseInt(inputValue);
          if (n < 1 || n > incr.get()) {
            System.out.print(messageService.getMessage("test.answer.retry"));
          } else {
            if (question.getAnswers().get(n - 1).getFlag()) {
              succsess++;
            } else {
              fail++;
            }
            checker = false;
          }
        } catch (Exception e) {
          System.out.print(messageService.getMessage("test.answer.retry"));
        }
      }
    }
    System.out.println(messageService.getMessage("test.result.label"));
    System.out.println(MessageFormat.format(messageService.getMessage("test.count.success"), succsess));
    System.out.println(MessageFormat.format(messageService.getMessage("test.count.fail"), fail));
  }
}
