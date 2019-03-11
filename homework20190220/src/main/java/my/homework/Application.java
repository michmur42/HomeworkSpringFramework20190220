package my.homework;

import my.homework.dao.FileQuestionsDAOImpl;
import my.homework.dao.QuestionDAO;
import my.homework.service.MessageServiceImpl;
import my.homework.service.TestingService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@Configuration
@ComponentScan
@PropertySource("classpath:/application.properties")
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        TestingService testingService = context.getBean(TestingService.class);
        testingService.start();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public QuestionDAO questionDAO(MessageServiceImpl messageService){
        Resource questions = new ClassPathResource("questions.csv");
        Resource answers = new ClassPathResource("answers.csv");
        return new FileQuestionsDAOImpl(messageService, questions, answers);
    }
}
