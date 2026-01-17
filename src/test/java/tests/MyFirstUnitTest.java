package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.*;

public class MyFirstUnitTest {

    @Test
    public void myTest() {
        System.out.println("🚀 Запускаю мой первый UI тест!");

        // 1. Настрой WebDriverManager
        WebDriverManager.chromedriver().setup();
        System.out.println("✅ WebDriverManager настроен");

        // 2. Создай ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // 3. Проверь если мы в CI
        boolean isCI = System.getenv("CI") != null && System.getenv("CI").equals("true");
        if (isCI) {
            options.addArguments("--headless", "--no-sandbox");
            System.out.println("🌐 Режим: Headless (CI)");
        } else {
            options.addArguments("--start-maximized");
            System.out.println("💻 Режим: Обычный (локально)");
        }

        // 4. Создай драйвер
        ChromeDriver driver = new ChromeDriver(options);
        System.out.println("✅ Браузер создан");

        try {
            // 5. Открой страницу
            driver.get("https://example.com");
            System.out.println("📄 Открыта страница: https://example.com");

            // 6. Получи заголовок
            String title = driver.getTitle();
            System.out.println("Заголовок страницы: " + title);

            // 7. Проверь что заголовок содержит нужный текст
            assertTrue(title.contains("Example Domain"),
                    "Заголовок должен содержать 'Example Domain'. Получено: " + title);

            // 8. Выведи сообщение об успехе
            System.out.println("✅ ТЕСТ ПРОЙДЕН! Мой первый UI тест работает!");

        } finally {
            // 9. Закрой браузер
            driver.quit();
            System.out.println("👋 Браузер закрыт");
        }
    }
}

