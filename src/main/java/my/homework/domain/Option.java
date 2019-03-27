package my.homework.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Вариант ответа
 */
public class Option {
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

  public static class Builder {

    private String language;
    private Integer questionId;
    private String text;
    private Boolean flag;


    private Builder() {}
    public static Builder newInstance(){
      return new Builder();
    }

    public Builder language(String language) {
      this.language = language;
      return this;
    }

    public Builder id(Integer questionId) {
      this.questionId = questionId;
      return this;
    }

    public Builder text(String textl) {
      this.text = text;
      return this;
    }

    public Builder flag(Boolean flag) {
      this.flag = flag;
      return this;
    }

    public Option build() {
      Option option = new Option();
      option.setQuestionId(this.questionId);
      option.setLanguage(this.language);
      option.setText(this.text);
      option.setFlag(this.flag);
      return option;
    }
  }
}
