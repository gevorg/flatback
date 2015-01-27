package component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.junit.Assert.*;

/**
 * Page for scanword.ru crossword page.
 *
 * @author gevorg
 */
public class ScanwordPage extends LoadableComponent<ScanwordPage> {
    // We driver.
    private final WebDriver driver;

    // Username.
    private final String username;

    // Password.
    private final String password;

    /**
     * Constructor.
     *
     * @param username username.
     * @param password password.
     * @param driver web driver.
     */
    public ScanwordPage(WebDriver driver, String username, String password) {
        this.driver = driver;
        this.username = username;
        this.password = password;

        // This call sets the WebElement fields.
        PageFactory.initElements(driver, this);
    }

    /**
     * Loading page.
     */
    @Override
    protected void load() {
        // Load page.
        driver.get("http://www.scanword.ru/contest/");

        // Login.
        login();

        // Maximize browser.
        driver.manage().window().maximize();
    }

    /**
     * Login method.
     */
    private void login() {
        // Username.
        WebElement usernameElem = driver.findElement(By.name("USER_LOGIN"));
        usernameElem.sendKeys(username);

        // Password.
        WebElement passwordElem = driver.findElement(By.name("USER_PASSWORD"));
        passwordElem.sendKeys(password);

        // Login.
        WebElement loginElem = driver.findElement(By.name("Login"));
        loginElem.click();
    }

    /**
     * Check if page is loaded.
     *
     * @throws Error if not loaded.
     */
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();

        assertTrue("Not on the scanword.ru contest page: " + url, url.contains("/contest/"));
    }
}
