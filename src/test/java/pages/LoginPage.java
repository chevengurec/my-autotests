package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    private WebDriver driver;

    @FindBy(id="email")
    private WebElement emailField;
    @FindBy(id="passwd")
    private WebElement passwordField;
    @FindBy(id="SubmitLogin")
    private WebElement signButton;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввести email")
    public void enterEmail (String email) {
        emailField.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword (String password) {
        passwordField.sendKeys(password);
    }

    @Step("Нажать submit")
    public void pressSubmit () {
        signButton.click();
    }
    @Step("Ввести email, пароль и нажать кнопку авторизации")
    public void login (String email, String password) {
        enterEmail(email);
        enterPassword(password);
        pressSubmit();
    }

}
