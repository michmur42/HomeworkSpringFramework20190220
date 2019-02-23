package my.homework.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;
import java.util.List;

public class Question {
    @CsvBindByPosition(position = 0)
    private Integer id;
    @CsvBindByPosition(position = 1)
    private String text;
    private List<Answer> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        if (this.answers == null){
            this.answers = new ArrayList<Answer>();
        }
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
