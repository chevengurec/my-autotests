package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void setUp () {
        driver = DriverManager.createDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void tearDown() {
        DriverManager.quitDriver(driver);
    }

    protected void openLoginPage() {
        driver.get("http://automationpractice.pl/index.php?controller=authentication");
    }
}
