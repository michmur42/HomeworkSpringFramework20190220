package my.homework.service;

import my.homework.domain.Option;
import my.homework.domain.Question;
import my.homework.exception.StopException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest(properties = "spring.main.banner-mode=off")
@RunWith(SpringRunner.class)
class QuestionServiceImplTest {

  @MockBean
  private MessageServiceImpl messageService;

  @MockBean
  private InputService inputService;

  @MockBean
  private TestingService testingService;

  @Autowired
  private QuestionServiceImpl questionService;

  @Test
  void doAsk() throws NoSuchFieldException, IOException, StopException {
    given(messageService.getMessage("prompt.retry")).willReturn("prompt.retry");
    given(messageService.getMessage("prompt.question")).willReturn("prompt.question");
    given(messageService.getMessage("prompt.option")).willReturn("prompt.option");
    given(messageService.getMessage("prompt.ask")).willReturn("prompt.ask");
    given(inputService.waitInput()).willReturn("2");
    Question question = Question.Builder.newInstance()
            .addOption(Option.Builder.newInstance().flag(false).build())
            .addOption(Option.Builder.newInstance().flag(true).build()) //choice option
            .build();
    assertTrue(questionService.doAsk(question).getChoice().getFlag(), "Error test doAsk");
  }
}