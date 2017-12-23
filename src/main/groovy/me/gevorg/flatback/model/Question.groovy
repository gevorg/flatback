package me.gevorg.flatback.model

import groovy.transform.ToString

/**
 * Model for questions.
 *
 * @author Gevorg Harutyunyan
 */
@ToString
class Question {
    String id
    String title
    List<Answer> answers = []

    /**
     * Returns pattern string based on answer values.
     *
     * @return pattern string.
     */
    String getPattern() {
        return answers.collect { it.value }.join()
    }

    /**
     * Returns true if patterns does not have '*', else false.
     *
     * @return true if patterns does not have '*', else false.
     */
    boolean isDone() {
        return !pattern.contains("*")
    }
}
