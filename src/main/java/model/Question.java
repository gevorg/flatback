package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for question.
 *
 * @author gevorg
 */
public class Question {
    // Id.
    private final String id;

    // Title of question.
    private String title;

    // List of answers.
    private List<Answer> answers;

    /**
     * Constructor.
     *
     * @param id id.
     * @param title title.
     */
    public Question(String id, String title) {
        this.id = id;
        this.title = title;
        this.answers = new ArrayList<Answer>();
    }

    /**
     * Returns pattern string based on answer values.
     *
     * @return pattern string.
     */
    public String getPattern() {
        StringBuilder builder = new StringBuilder();

        for (Answer answer: answers) {
            builder.append(answer.getValue());
        }

        return builder.toString();
    }

    /**
     * Returns true if patterns does not have '*', else false.
     *
     * @return true if patterns does not have '*', else false.
     */
    public boolean isDone() {
        return !getPattern().contains("*");
    }

    /**
     * Adds answer.
     *
     * @param answer answer.
     */
    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.addQuestion(this);
    }

    /**
     * Set answers based on input string.
     *
     * @param input input string.
     */
    public void setAnswers(String input) {
        for (int i = 0; i < answers.size(); ++i) { // Updating answers.
            Answer answer = answers.get(i);
            answer.setValue(input.charAt(i));
        }
    }

    /**
     * Getter for answers.
     *
     * @return list of answers.
     */
    public List<Answer> getAnswers() {
        return answers;
    }

    /**
     * Getter for id.
     *
     * @return id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns title.
     *
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Overriding {@link Object#toString()} method.
     *
     * @return {id(pattern) - title} string.
     */
    @Override
    public String toString() {
        return "{" + id + "(" + getPattern() + ") - " + title + "}";
    }
}
