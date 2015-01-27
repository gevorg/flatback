package component;

import model.Answer;
import model.Question;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Crossword component.
 *
 * @author gevorg
 */
public class Crossword extends LoadableComponent<Crossword> {
    // Web driver.
    private final WebDriver driver;

    // Parent component.
    private LoadableComponent parent;

    // Crossword element.
    @FindBy(id = "scanword")
    private WebElement crossword;

    // Questions to speed up solving process.
    private Map<String, WebElement> questionElementMap;

    // Answers to speed up solving process.
    private Map<String, List<WebElement>> answerElementMap;

    /**
     * Constructor.
     *
     * @param driver web driver.
     * @param parent parent component.
     */
    public Crossword(WebDriver driver, LoadableComponent parent) {
        this.driver = driver;
        this.parent = parent;

        // This call sets the WebElement fields.
        PageFactory.initElements(driver, this);
    }

    /**
     * Loading component.
     */
    @Override
    protected void load() {
        // Load parent.
        parent.get();
    }

    /**
     * Tries to answer question, returns true if answer was successful.
     *
     * @param question question to answer.
     * @param possibleAnswer possible answer.
     * @return true if correct, else false.
     */
    public boolean tryAnswer(Question question, String possibleAnswer) {
        // Focus for typing.
        WebElement questionElem = questionElementMap.get(question.getId());
        questionElem.click();

        // Do typing.
        List<WebElement> questionAnswers = answerElementMap.get(question.getId());
        for (int i = 0; i < questionAnswers.size(); ++i) {
            WebElement answerInput = questionAnswers.get(i);
            answerInput.sendKeys(possibleAnswer.substring(i, i + 1));
        }

        // If question is disabled then attempt is successful.
        return questionElem.getAttribute("class").contains("disabled-td");
    }

    /**
     * Loading questions.
     */
    public List<Question> loadQuestions() {
        questionElementMap = new HashMap<>(); // Speed up.

        Map<String, Question> questionMap = new TreeMap<>();
        List<WebElement> questionElements = crossword.findElements(By.className("question"));

        // Process questions.
        for (WebElement questionElement : questionElements) {
            String id = questionElement.getAttribute("id");
            String title = questionElement.getAttribute("title");

            questionMap.put(id, new Question(id, title));
            questionElementMap.put(id, questionElement); // Speed up.
        }

        answerElementMap = new HashMap<>(); // Speed up.
        List<WebElement> answerElements = crossword.findElements(By.className("answer"));

        // Adding answers.
        for (WebElement answerElem : answerElements) {
            WebElement answerInputElem = answerElem.findElement(By.tagName("input")); // Speed up.

            Answer answer = new Answer(answerElem.getAttribute("id"));
            String[] classTokens = answerElem.getAttribute("class").split(" ");

            for (String classToken: classTokens) {
                // Process only question tokens.
                if (classToken.startsWith("q") && questionMap.containsKey(classToken)) {
                    Question question = questionMap.get(classToken);
                    question.addAnswer(answer);

                    if (answerElementMap.containsKey(classToken)) { // Speed up.
                        answerElementMap.get(classToken).add(answerInputElem);
                    } else { // Speed up.
                        List<WebElement> questionAnswerElements = new ArrayList<>();
                        questionAnswerElements.add(answerInputElem);
                        answerElementMap.put(classToken, questionAnswerElements);
                    }
                }
            }
        }

        return new ArrayList<>(questionMap.values());
    }

    /**
     * Throws error if component is not loaded.
     *
     * @throws Error if component is not loaded.
     */
    @Override
    protected void isLoaded() throws Error {
        try {
            crossword.click();
        } catch (NoSuchElementException e) {
            fail("Crossword should be loaded.");
        }
    }
}
