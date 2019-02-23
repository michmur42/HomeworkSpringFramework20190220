package my.homework.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import my.homework.dao.QuestionDAO;
import my.homework.domain.Answer;
import my.homework.domain.Question;
import my.homework.service.MessageService;
import my.homework.util.CSVHelper;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionCsvDAOImpl implements QuestionDAO {

    private final Resource questionResource;
    private final Resource answerResource;
    private MessageService messageService;

    public QuestionCsvDAOImpl(Resource questionResource, Resource answerResource, MessageService messageService) {
        this.questionResource = questionResource;
        this.answerResource = answerResource;
        this.messageService = messageService;
    }

    public List<Question> getQuestions() {
        try {
            List<Answer> answers = CSVHelper.readFromCSV(answerResource,Answer.class);
            return CSVHelper.readFromCSV(questionResource,Question.class).stream()
                    .peek(q->q.setAnswers(answers.stream()
                            .filter(a->a.getQuestionId().equals(q.getId()))
                            .collect(Collectors.toList())))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(messageService.getMessage("error.parse.csvfile"), e);
        }
    }
}
