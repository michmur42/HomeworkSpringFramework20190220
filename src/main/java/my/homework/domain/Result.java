package my.homework.domain;

import java.util.ArrayList;
import java.util.List;

public class Result {
  private String user;
  private List<Answer> answers;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public List<Answer> getAnswers() {
    if (answers == null) {
      answers = new ArrayList<>();
    }
    return answers;
  }

  public void setAnswers(List<Answer> list) {
    this.answers = list;
  }
}
