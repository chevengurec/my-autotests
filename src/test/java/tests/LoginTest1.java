package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest1 extends BaseTest {

    private LoginPage loginPage;
    private static final String WRONG_EMAIL = "test@";
    private static final String PASSWORD = "12345";

    @BeforeEach
    public void setUpTest() {

        //устанавливаем драйвер и его опции
        setUp();

        // открываем страницу с логином
        openLoginPage();

        // создаем объект страницы, передавая в него драйвер
        loginPage = new LoginPage(driver);
    }


    @AfterEach
    public void tearDownTest() {
        tearDown();
    }

    // Тесты будут здесь
    @Test
    public void loginPageShouldOpen () {

       String currentURL = driver.getCurrentUrl();
       String pageTitle = driver.getTitle().toLowerCase();
       WebElement emailField = driver.findElement(By.id("email"));

       assertTrue(currentURL.contains("authentication"));

       assertTrue(pageTitle.contains("login")
               || pageTitle.contains("authentication"));

       assertTrue(emailField.isDisplayed(), "Поле email должно отображаться");

    }

    @Test
    public void invalidLoginShouldShowError() {
        // Используй loginPage.enterEmail(), enterPassword(), clickSignIn()
        // Используй wait.until() для ожидания ошибки
        // Сделай проверку assertTrue()
        loginPage.login(WRONG_EMAIL, PASSWORD);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
        String errorMessageText = errorMessage.getText().toLowerCase();
        assertTrue(errorMessageText.toLowerCase().contains("authentication failed") || errorMessageText.toLowerCase().contains("invalid email"));

    }

}
