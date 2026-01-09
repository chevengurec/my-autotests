package tests;

import pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {

    @Test
    public void openWebsiteTest() {

        //скачиваем и устанавливаем chromeDriver
        WebDriverManager.chromedriver().setup();

        //создаем экземпляр драйвера
        WebDriver driver = new ChromeDriver();

        //открываем тестовый сайт с помощью экземпляра драйвера
        driver.get("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account");

        try { Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LoginPage loginPage = new LoginPage(driver);


        loginPage.login("test@test.com", "12345");

        try { Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(); }

        String currentURL = driver.getCurrentUrl();
        System.out.println("Текущий URL: " + currentURL);

        if (currentURL.contains("my-account")) {
            System.out.println("✅ Логин успешен! Мы на странице аккаунта.");
        } else {
            System.out.println("❌ Логин не удался. Остались на странице логина.");

            try {
                WebElement error = driver.findElement(By.cssSelector(".alert.alert-danger"));
                System.out.println("Сообщение об ошибке: " + error.getText());
            } catch (Exception e) {
                System.out.println("Нет сообщения об ошибке, но логин не прошёл.");
            }

        }

            //Закрываем драйвер
        driver.quit();

    }
}
