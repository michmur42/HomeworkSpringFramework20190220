package my.homework.service;

import my.homework.domain.Option;
import my.homework.domain.Question;
import my.homework.exception.StopException;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.reflection.FieldSetter;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionServiceImplTest {

  private MessageService messageService = mock(MessageService.class);
  private BufferedReader bufferedReader = mock(BufferedReader.class);

  @Test
  void doAsk() throws NoSuchFieldException, IOException, StopException {
    when(messageService.getMessage("prompt.retry")).thenReturn("prompt.retry");
    when(messageService.getMessage("prompt.question")).thenReturn("prompt.question");
    when(messageService.getMessage("prompt.option")).thenReturn("prompt.option");
    when(messageService.getMessage("prompt.ask")).thenReturn("prompt.ask");
    when(bufferedReader.readLine()).thenReturn("2");
    QuestionServiceImpl questionService = new QuestionServiceImpl(messageService);
    FieldSetter.setField(questionService, questionService.getClass().getDeclaredField("bufferedReader"), bufferedReader);
    Question question = new Question();
    Option option1 = new Option();
    option1.setFlag(false);
    question.getOptions().add(option1);
    Option option2 = new Option();
    option2.setFlag(true); //Choice option
    question.getOptions().add(option2);
    assertTrue(questionService.doAsk(question).getChoice().getFlag(), "Error test doAsk");
  }
}