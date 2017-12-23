package me.gevorg.flatback.model

import groovy.transform.ToString

/**
 * Answer cell representation.
 *
 * @author Gevorg Harutyunyan
 */
@ToString(excludes = ['questions'])
class Answer {
    String id
    Character value = '*'
    List<Question> questions = []
}
