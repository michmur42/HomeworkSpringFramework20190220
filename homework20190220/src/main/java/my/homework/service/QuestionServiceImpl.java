package my.homework.service;

import my.homework.domain.Answer;
import my.homework.domain.Option;
import my.homework.domain.Question;
import my.homework.exception.StopException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;

@Service
public class QuestionServiceImpl implements QuestionService {
  private final MessageService messageService;
  private BufferedReader bufferedReader;

  public QuestionServiceImpl(MessageService messageService) {
    this.messageService = messageService;
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  }

  @Override
  public Answer doAsk(Question question) throws IOException, StopException {
    printQuestion(question);
    Answer answer = new Answer();
    answer.setQuestion(question);
    while (true) {
      String in = bufferedReader.readLine();
      try {
        if ("q".equals(in)) {
          throw new StopException();
        }
        int numOption = Integer.parseInt(in);
        if (numOption < 1 || numOption > question.getOptions().size()) {
          System.out.print(messageService.getMessage("prompt.retry"));
          continue;
        }
        answer.setChoice(question.getOptions().get(numOption - 1));
        break;
      } catch (NumberFormatException ex) {
        System.out.print(messageService.getMessage("prompt.retry"));
      }
    }
    return answer;
  }

  private void printQuestion(Question question) {
    System.out.println();
    System.out.println(MessageFormat.format(messageService.getMessage("prompt.question"), question.getText()));
    System.out.println();
    int num = 0;
    for (Option option : question.getOptions()) {
      num++;
      System.out.println(MessageFormat.format(messageService.getMessage("prompt.option"), num, option.getText()));
    }
    System.out.print(messageService.getMessage("prompt.ask"));
  }
}
