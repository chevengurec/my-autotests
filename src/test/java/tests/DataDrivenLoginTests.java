package tests;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Авторизация пользователей")
@Feature("Логин на сайте")
public class DataDrivenLoginTests extends BaseTest {

    private static final String EMAIL = "test@test.ru";
    private static final String WRONG_EMAIL = "test@";
    private static final String PASSWORD = "12345";
    private static final String WRONG_PASSWORD = "фывфвф";


    private LoginPage loginPage;

    @BeforeEach
    public void setupTest () {

        //устанавливаем драйвер и его опции
        setUp();

        //открываем страницу
        openLoginPage();

        //создаем экхемпляр страницы и передаем в нее драйвео
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }

    private void clearLoginFields() {
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("passwd")).clear();
    }

    @Story("Неудачные попытки email")
    @Description("Тест проверяет различные некорректные email с правильным паролем")
    @ParameterizedTest
    @ValueSource(strings = {"wrong@test.com", "invalid.email", "test@", "@domain.com"})
    void loginWithDifferentInvalidEmailsShouldFail(String email) {

        loginPage.login(email,PASSWORD);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
        String errorMessageText = errorMessage.getText().toLowerCase(Locale.ROOT);

        assertTrue(errorMessageText.contains("authentication failed") || errorMessageText.toLowerCase().contains("invalid email"));
        clearLoginFields();

    }
    @Story("Неудачные попытки email или пароля")
    @Description("Тест проверяет различные некорректные email с правильным паролем или наоборот")
    @ParameterizedTest
    @CsvSource({
            "wrong@test.com, 000000",
            "test@test.ru, ------",
            "invalid.email, 12345",
            "'', password",  // пустой email
            "test@test.com, ''"  // пустой пароль
    })
    public void loginWithDifferentCredentialsShouldFail(String email, String password) {
        loginPage.login(email, password);
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
        String errorMessageText = errorMessage.getText().toLowerCase();
        assertTrue(
                errorMessageText.contains("authentication failed") ||
                        errorMessageText.contains("invalid") ||
                        errorMessageText.contains("required"),
                "Текст ошибки: " + errorMessageText
        );

    }


}
