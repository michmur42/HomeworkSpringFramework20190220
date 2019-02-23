package my.homework.domain;

import com.opencsv.bean.CsvBindByPosition;

public class Answer {
    @CsvBindByPosition(position = 0)
    private Integer questionId;
    @CsvBindByPosition(position = 1)
    private String text;
    @CsvBindByPosition(position = 2)
    private Boolean flag;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
