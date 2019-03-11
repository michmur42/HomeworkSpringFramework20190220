package my.homework.dao;

import my.homework.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileQuestionsDAOImplTest {

    private MessageService messageService = mock(MessageService.class);
    private FileQuestionsDAOImpl fileQuestionsDAO;

    @Test
    void getQuestionsError() {
        Resource questions = new ClassPathResource("dummy.csv");
        Resource answers = new ClassPathResource("dummy.csv");
        when(messageService.getMessage("error.parse.csvfile")).thenReturn("Error parse CSV file");
        fileQuestionsDAO = new FileQuestionsDAOImpl(messageService, questions, answers);
        assertThrows(RuntimeException.class, () -> fileQuestionsDAO.getQuestions(new Locale("ru")));
    }

    @Test
    void getQuestionsSuccsess() {
        Resource questions = new ClassPathResource("questions.csv");
        Resource answers = new ClassPathResource("answers.csv");
        when(messageService.getMessage("error.parse.csvfile")).thenReturn("Error parse CSV file");
        fileQuestionsDAO = new FileQuestionsDAOImpl(messageService, questions, answers);
        assertEquals(1, fileQuestionsDAO.getQuestions(new Locale("ru")).size());
    }
}