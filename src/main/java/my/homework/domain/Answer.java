package my.homework.domain;

/**
 * Ответ пользователя
 */
public class Answer {
    /**
     * Вопрос
     */
    private Question question;

    /**
     * Выбор пользователя
     */
    private Option choice;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getChoice() {
        return choice;
    }

    public void setChoice(Option choice) {
        this.choice = choice;
    }
}
