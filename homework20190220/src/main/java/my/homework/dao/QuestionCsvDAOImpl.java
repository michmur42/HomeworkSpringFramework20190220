package my.homework.dao;

import my.homework.domain.Answer;
import my.homework.domain.Question;
import my.homework.service.MessageService;
import my.homework.util.CSVHelper;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class QuestionCsvDAOImpl implements QuestionDAO {

    private final Resource questionResource;
    private final Resource answerResource;
    private MessageService messageService;

    public QuestionCsvDAOImpl(Resource questionResource, Resource answerResource, MessageService messageService) {
        this.questionResource = questionResource;
        this.answerResource = answerResource;
        this.messageService = messageService;
    }

    public List<Question> getQuestions(Locale locale) {
        try {
            List<Answer> answers = CSVHelper.readFromCSV(answerResource, Answer.class);
            return CSVHelper.readFromCSV(questionResource, Question.class).stream()
                    .filter(q->locale.getLanguage().equals(q.getLanguage()))
                    .peek(q -> q.setAnswers(answers.stream()
                            .filter(a-> locale.getLanguage().equals(locale.getLanguage()))
                            .filter(a -> a.getQuestionId().equals(q.getId()))
                            .collect(Collectors.toList())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(messageService.getMessage("error.parse.csvfile"), e);
        }
    }
}
