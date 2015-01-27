package component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Page for loopy.ru.
 *
 * @author gevorg
 */
public class LoopyPage extends LoadableComponent<LoopyPage> {
    // Web driver.
    private final WebDriver driver;

    // Search pattern.
    private String pattern;

    // Search title.
    private String title;

    /**
     * Constructor.
     *
     * @param driver web driver.
     * @param pattern search pattern.
     * @param title search title.
     */
    public LoopyPage(WebDriver driver, String pattern, String title) {
        this.driver = driver;
        try {
            this.pattern = URLEncoder.encode(pattern, "UTF-8");
            this.title = URLEncoder.encode(title, "UTF-8");
        } catch (Exception ignored) { /* Ignoring it */ }

        // This call sets the WebElement fields.
        PageFactory.initElements(driver, this);
    }

    /**
     * Extracts hints from page.
     *
     * @return list of hints.
     */
    public List<String> getHints() {
        List<String> hints = new ArrayList<>();

        // Extracting hints.
        List<WebElement> hintElements = driver.findElements(By.cssSelector("div.wd > h3 > a"));
        for (WebElement hint: hintElements) {
            hints.add( hint.getText() ); // Push hint into collection.
        }

        return hints;
    }

    /**
     * Load method.
     */
    @Override
    protected void load() {
        // Load page.
        driver.get("http://loopy.ru/?word=" + pattern + "&def=" + title);
    }

    /**
     * Throws error if loopy.ru is not loaded.
     *
     * @throws Error if not loaded.
     */
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();

        assertTrue("Not on the loopy.ru page: " + url, url.endsWith("?word=" + pattern + "&def=" + title));
    }
}
