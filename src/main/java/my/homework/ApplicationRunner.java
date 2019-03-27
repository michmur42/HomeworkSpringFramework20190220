package my.homework;

import my.homework.domain.Answer;
import my.homework.exception.StopException;
import my.homework.service.TestingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

  private final TestingService testingService;


  public ApplicationRunner(TestingService testingService) {
    this.testingService = testingService;
  }

  @Override
  public void run(String... args) throws Exception {
    try {
      List<Answer> history = testingService.start();
      testingService.validate(history);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    } catch (StopException e) {
      System.exit(0);
    }
  }
}
