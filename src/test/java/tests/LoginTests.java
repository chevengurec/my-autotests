package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void setUpTest() {
        setUp();
        openLoginPage();
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }



    @Test
    public void invalidLoginShouldShowErrorMessage() {
        loginPage.login("test@", "12345");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert.alert-danger")));

        assertTrue(driver.getCurrentUrl().contains("authentication"),
                "Должны остаться на странице логина");

        assertTrue(errorMessage.isDisplayed(),
                "Сообщение об ошибке должно отображаться");

        String errorText = errorMessage.getText().toLowerCase();
        assertTrue(errorText.contains("authentication failed") ||
                        errorText.contains("invalid email"),
                "Текст ошибки должен быть корректным: " + errorText);
    }

    @Test
    public void loginWithEmptyCredentialsShouldShowError() {
        loginPage.login("", "");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert.alert-danger")));

        String errorText = errorMessage.getText().toLowerCase();
        assertTrue(errorText.contains("required") &&
                        (errorText.contains("email") || errorText.contains("password")),
                "Должна быть ошибка о обязательных полях: " + errorText);
    }

    @Test
    public void validEmailFormatButWrongPassword() {

        loginPage.login("test@test.com", "абвгд");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert.alert-danger")));

        assertTrue(errorMessage.getText().toLowerCase().contains("authentication failed"),
                "При валидном email, но неверном пароле должна быть 'Authentication failed'");
    }
}

