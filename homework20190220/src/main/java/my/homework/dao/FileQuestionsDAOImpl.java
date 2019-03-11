package my.homework.dao;

import my.homework.domain.Answer;
import my.homework.domain.Question;
import my.homework.service.MessageServiceImpl;
import my.homework.util.CSVHelper;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Данный класс реализует интерфейс QuestionDAO и предоставляет доступ к списку вопросов
 * и ответов из файлов questions.csv и answers.csv находящихся в classpath
 */
public class FileQuestionsDAOImpl implements QuestionDAO {

    /**
     * Файл с вопросами
     */
    private final Resource questionResource;

    /**
     * Файл с ответами
     */
    private final Resource answerResource;

    /**
     * Сервис для работы с message bundle
     */
    private MessageServiceImpl messageService;

    public FileQuestionsDAOImpl(MessageServiceImpl messageService, Resource questionResource, Resource answerResource ) {
        this.questionResource = questionResource;
        this.answerResource = answerResource;
        this.messageService = messageService;
    }

    /**
     * Возвращает список с вопросами и ответами
     *
     * @param locale локализация
     * @return список вопросов
     */
    public List<Question> getQuestions(Locale locale) {
        try {
            List<Answer> answers = CSVHelper.readFromCSV(answerResource, Answer.class);
            return CSVHelper.readFromCSV(questionResource, Question.class).stream()
                    .filter(q -> locale.getLanguage().equals(q.getLanguage()))
                    .peek(q -> q.setAnswers(answers.stream()
                            .filter(a -> locale.getLanguage().equals(locale.getLanguage()))
                            .filter(a -> a.getQuestionId().equals(q.getId()))
                            .collect(Collectors.toList())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(messageService.getMessage("error.parse.csvfile"), e);
        }
    }
}
