import component.Crossword;
import component.LoopyPage;
import component.ScanwordPage;
import model.Answer;
import model.Question;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.Console;
import java.util.List;
import java.util.Stack;

/**
 * Main class. Tries to solve crossword.
 *
 * @author gevorg
 */
public class Flatback {
    // List of questions.
    private List<Question> questions;

    // Crossword component.
    private Crossword crossword;

    // Loopy driver.
    private WebDriver loopyDriver;

    /**
     * Main method.
     *
     * @param args input arguments.
     */
    public static void main(String[] args) {
        Console console = System.console();
        String username = console.readLine("\n\nPlease enter scanword.ru username: ");
        String password = String.valueOf(console.readPassword("Please enter scanword.ru password: "));

        Flatback app = new Flatback(username, password);
        app.tryToSolve();
    }

    /**
     * Tries to solve the crossword.
     */
    public void tryToSolve() {
        Stack<Question> questionsToSolve = new Stack<>();
        questionsToSolve.addAll(questions); // Add questions to stack.


        Stack<Question> questionsWithNoHints = new Stack<>(); // Second attempt questions.

        do { // Process questions.
            if ( questionsToSolve.isEmpty() ) {
                // Retry no hint questions after all others are done.
                questionsToSolve.addAll(questionsWithNoHints);
                questionsWithNoHints.clear();
            }

            Question question = questionsToSolve.pop();

            // Skipping done questions.
            if ( question.isDone() || questionsWithNoHints.contains(question) ) {
                continue;
            }

            List<String> hints = getHints(question); // Get hints and try them.
            if (hints.isEmpty()) {
                String title = question.getTitle();

                if ( !title.isEmpty() ) {
                    int lastWordPos = title.lastIndexOf(" ") + 1; // Removing the last word.

                    question.setTitle(title.substring(0, lastWordPos).trim());
                    questionsWithNoHints.push(question); // Retry modified question after all others are done.
                }
            } else {
                for (String hint : hints) {
                    // Try to solve.
                    if (crossword.tryAnswer(question, hint)) {
                        question.setAnswers(hint);

                        for (Answer answer : question.getAnswers()) {
                            for (Question answerQuestion : answer.getQuestions()) {
                                if ( !answerQuestion.isDone() && !questionsToSolve.contains(answerQuestion) ) {
                                    questionsToSolve.push(answerQuestion); // Retry after change.
                                }
                            }
                        }

                        break; // Done with this one.
                    }
                }
            }

        } while (!questionsToSolve.isEmpty() || !questionsWithNoHints.isEmpty());
    }

    /**
     * Returns list of hints.
     *
     * @param question question to search.
     * @return list of hints.
     */
    private List<String> getHints(Question question) {
        LoopyPage loopyPage = new LoopyPage(loopyDriver, question.getPattern(), question.getTitle());

        // Extracting hints.
        return loopyPage.get().getHints();
    }

    /**
     * Constructor.
     */
    public Flatback(String username, String password) {
        // Initializing new driver.
        WebDriver driver = new FirefoxDriver();

        // Open crossword.
        ScanwordPage scanwordPage = new ScanwordPage(driver, username, password);
        crossword = new Crossword(driver, scanwordPage);
        crossword.get();

        // Load questions.
        questions = crossword.loadQuestions();

        // Init loopy driver.
        loopyDriver = new HtmlUnitDriver();
    }
}
