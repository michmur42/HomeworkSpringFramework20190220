package my.homework.domain;

import com.opencsv.bean.CsvBindByPosition;

public class Answer {
    /**
     * Язык
     */
    @CsvBindByPosition(position = 0)
    private String language;

    /**
     * Идентификатор вопроса
     */
    @CsvBindByPosition(position = 1)
    private Integer questionId;

    /**
     * Текст варианта
     */
    @CsvBindByPosition(position = 2)
    private String text;

    /**
     * Признак корректности ответа
     */
    @CsvBindByPosition(position = 3)
    private Boolean flag;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

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
