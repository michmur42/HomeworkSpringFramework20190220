package my.homework.dao;

import my.homework.service.MessageService;
import my.homework.service.MessageServiceImpl;
import my.homework.service.TestingService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@SpringBootTest(properties = "spring.main.banner-mode=off")
@RunWith(SpringRunner.class)
class FileQuestionsDAOImplTest {

  @MockBean
  private MessageServiceImpl messageService;

  @MockBean
  private TestingService testingService;

  @Autowired
  private FileQuestionsDAOImpl fileQuestionsDAO;

  @Before
  void before() {
    given(messageService.getMessage("error.parse.csvfile")).willReturn("Error parse CSV file");
  }

  @Test
  void getQuestionsError() {
    fileQuestionsDAO.setAnswerResource(new ClassPathResource("dummy.csv"));
    fileQuestionsDAO.setQuestionResource(new ClassPathResource("dummy.csv"));
    assertThrows(RuntimeException.class, () -> fileQuestionsDAO.getQuestions(new Locale("ru")));
  }

  @Test
  void getQuestionsSuccsess() {
    fileQuestionsDAO.setAnswerResource(new ClassPathResource("questions.csv"));
    fileQuestionsDAO.setQuestionResource(new ClassPathResource("options.csv"));
    assertEquals(15, fileQuestionsDAO.getQuestions(new Locale("ru")).size());
  }


}