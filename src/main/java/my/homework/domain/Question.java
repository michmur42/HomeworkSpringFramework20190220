package my.homework.domain;

import com.opencsv.bean.CsvBindByPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * Вопрос
 */
public class Question {
  /**
   * Язык
   */
  @CsvBindByPosition(position = 0)
  private String language;

  /**
   * Идентификатор вопроса
   */
  @CsvBindByPosition(position = 1)
  private Integer id;

  /**
   * Текс вопроса
   */
  @CsvBindByPosition(position = 2)
  private String text;

  /**
   * Варианты ответов
   */
  private List<Option> options;

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

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

  public List<Option> getOptions() {
    if (this.options == null) {
      this.options = new ArrayList<Option>();
    }
    return this.options;
  }

  public void setOptions(List<Option> options) {
    this.options = options;
  }
}
