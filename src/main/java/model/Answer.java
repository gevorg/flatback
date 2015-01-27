package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Answer cell class.
 *
 * @author gevorg
 */
public class Answer {
    // Id.
    private final String id;

    // Value of cell.
    private Character value = '*';

    // List of questions.
    private List<Question> questions;

    /**
     * Constructor.
     *
     * @param id id.
     */
    public Answer(String id) {
        this.id = id;
        this.questions = new ArrayList<>();
    }

    /**
     * Adds question for answer.
     *
     * @param question question.
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Getter for questions.
     *
     * @return questions.
     */
    public List<Question> getQuestions() {
        return questions;
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
     * Sets value.
     *
     * @param value cell value.
     */
    public void setValue(Character value) {
        this.value = value;
    }

    /**
     * Returns value.
     *
     * @return value.
     */
    public Character getValue() {
        return value;
    }
}
