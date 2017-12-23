import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

environments {
    scanword {
        driver = {
            WebDriver driver = new ChromeDriver()
            driver.manage().window().maximize()
            driver
        }
    }
}
