package my.homework.service;

import my.homework.dao.QuestionDAO;
import my.homework.domain.Question;

import java.text.MessageFormat;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TestingServiceImpl implements TestingService {

    private final QuestionDAO questionDAO;
    private List<Question> questions;
    private MessageService messageService;

    public TestingServiceImpl(QuestionDAO questionDAO, MessageService messageService) {
        this.questionDAO = questionDAO;
        this.messageService = messageService;
    }

    public void init(){
        this.questions = questionDAO.getQuestions();
    }
    public void start() {
        int num = 0;
        int succsess = 0;
        int fail = 0;

        for (Question question : questions){
            num++;
            StringBuilder sb = new StringBuilder();
            sb.append("-------------------------------------------------------------------------------------\n");
            sb.append("Вопрос #" + num + ":\n");
            sb.append(question.getText());
            sb.append("\nВозможные ответы:\n");
            AtomicInteger incr = new AtomicInteger(0);
            question.getAnswers().stream().forEach(a->{
                incr.incrementAndGet();
                sb.append(MessageFormat.format("{0} - {1}\n",incr.get(), a.getText()).toString());
            });
            sb.append("\n Выберите ответ. Укажите номер варианта и нажмите Enter. (q-завершить тестирование):");
            System.out.println(sb.toString());
            Scanner scanner = new Scanner(System.in);
            boolean checker = true;
            while (checker){
                String inputValue = scanner.next();
                if ("q".equals(inputValue)){
                    return;
                }
                try{
                    Integer n = Integer.parseInt(inputValue);
                    if (n<1 || n>incr.get()){
                        System.out.println("Некорректный ответ. Попробуйте снова:");
                    } else {
                        if (question.getAnswers().get(n-1).getFlag()){
                            succsess++;
                        } else {
                            fail++;
                        }
                        checker = false;
                    }
                } catch (Exception e){
                    System.out.println("Некорректный ответ. Попробуйте снова:");
                }
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("--------------- РЕЗУЛЬТАТ ---------------\n");
        res.append("Количество правильных ответов: " + succsess); res.append("\n");
        res.append("Количество неправильных ответов: " + fail); res.append("\n");
        System.out.println(res.toString());
    }
}
