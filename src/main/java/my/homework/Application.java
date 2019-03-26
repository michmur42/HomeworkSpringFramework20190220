package my.homework;

import my.homework.domain.Answer;
import my.homework.exception.StopException;
import my.homework.service.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
public class Application implements CommandLineRunner {

  @Autowired
  private ApplicationContext context;

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Override
  public void run(String... args) throws Exception {
    TestingService testingService = context.getBean(TestingService.class);
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
