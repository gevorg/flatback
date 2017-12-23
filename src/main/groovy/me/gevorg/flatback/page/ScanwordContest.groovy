package me.gevorg.flatback.page

import geb.Page
import geb.module.FormElement
import geb.navigator.Navigator
import me.gevorg.flatback.model.Answer
import me.gevorg.flatback.model.Question

/**
 * Contest page for scanword.ru.
 *
 * @author Gevorg Harutyunyan
 */
class ScanwordContest extends Page {
    static url = "https://scanword.ru/contest/"
    static at = { title == "Конкурсный сканворд" }

    /**
     * Loads all questions with answers.
     *
     * @return all answers and questions.
     */
    Stack<Question> loadQuestionsAndAnswers() {
        Map<String, Question> questionMap = new TreeMap<>()

        $('.question').each {
            String id = it.@id
            questionMap.put(id, new Question(id: id, title: it.@title))
        }

        $('.answer').each {
            //noinspection GroovyAssignabilityCheck
            Answer answer = new Answer(id: it.@id, value: it.find('input').value() ?: '*')
            String className = it.@class

            className.split(" ").each {
                if (it.startsWith('q') && questionMap.containsKey(it)) {
                    Question question = questionMap[it]

                    question.answers << answer
                    answer.questions << question
                }
            }
        }

        return questionMap.values() as Stack<Question>
    }

    /**
     * Try answer for question.
     *
     * @param hints list of hints.
     * @param question question.
     * @return true if answer is correct, else false.
     */
    boolean tryAnswer(List<String> hints, Question question) {
        def questionElem = $("#${question.id}")
        def answerElems = $(".answer.${question.id} > input")

        boolean isDone = questionElem.@class.contains('disabled-td')
        hints.each {
            if (isDone) return true

            questionElem.click()

            String answer = it
            answerElems.eachWithIndex { Navigator entry, int i ->
                if (question.answers[i].value == '*' && entry.module(FormElement).isEditable()) {
                    entry.value(answer.substring(i, i + 1))
                }
            }

            isDone = questionElem.@class.contains('disabled-td')
            if (isDone) {
                question.answers.eachWithIndex { Answer ans, int i ->
                    ans.value = answer.charAt(i)
                }
            }
        }


        return isDone
    }
}
