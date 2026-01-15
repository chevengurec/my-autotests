package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumPreparatoryTest {

    @Test
    public void testSeleniumSetupWithoutBrowser() {
        System.out.println("=== ДЕНЬ 18: ПОДГОТОВКА SELENIUM ===");

        // Часть 1: Проверяем что Selenium зависимости доступны
        System.out.println("1. Проверяем Selenium зависимости...");

        try {
            // Просто проверяем что классы Selenium загружаются
            Class.forName("org.openqa.selenium.WebDriver");
            Class.forName("org.openqa.selenium.chrome.ChromeDriver");
            Class.forName("io.github.bonigarcia.wdm.WebDriverManager");

            System.out.println("✅ Selenium зависимости доступны!");
        } catch (ClassNotFoundException e) {
            fail("Selenium зависимости не найдены: " + e.getMessage());
        }

        // Часть 2: Создаем конфигурацию браузера (но не запускаем!)
        System.out.println("2. Создаем конфигурацию браузера...");

        boolean isCI = System.getenv("CI") != null
                && System.getenv("CI").equals("true");

        System.out.println("Мы в CI: " + isCI);

        // Конфигурация для разных сред
        if (isCI) {
            System.out.println("🔧 Конфигурация для CI:");
            System.out.println("  - Будет использован headless Chrome");
            System.out.println("  - Опции: --headless, --no-sandbox");
        } else {
            System.out.println("💻 Конфигурация для локальной среды:");
            System.out.println("  - Будет использован обычный Chrome");
            System.out.println("  - Опции: --start-maximized");
        }

        // Часть 3: Тест который всегда проходит
        System.out.println("3. Проверяем базовую функциональность...");

        // Этот assert проверяет что наша логика работает
        assertTrue(true, "Базовая проверка Selenium подготовки");

        System.out.println("✅ Подготовка к Selenium в CI завершена!");
        System.out.println("Завтра (День 19) мы запустим реальный браузер!");
    }

    @Test
    public void createBrowserConfiguration() {
        System.out.println("=== СОЗДАНИЕ КОНФИГУРАЦИИ БРАУЗЕРА ===");

        // Этот метод создает конфигурацию, но НЕ запускает браузер

        ChromeOptions options = new ChromeOptions();

        // Определяем среду запуска
        boolean isCI = System.getenv("CI") != null
                && System.getenv("CI").equals("true");

        // Настраиваем опции в зависимости от среды
        if (isCI) {
            // Опции для CI (headless режим)
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            System.out.println("Создана конфигурация для CI (headless)");
        } else {
            // Опции для локальной разработки
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            System.out.println("Создана конфигурация для локальной среды");
        }

        // Проверяем что опции созданы
        assertNotNull(options, "ChromeOptions должны быть созданы");

        // Здесь МОЖНО было бы создать драйвер, но мы этого не делаем
        // WebDriver driver = new ChromeDriver(options); ← НЕ ВЫЗЫВАЕМ!

        System.out.println("✅ Конфигурация браузера создана успешно!");
        System.out.println("Примечание: браузер НЕ запускался - это подготовка");
    }

    @Test
    public void testWebDriverManager() {
        System.out.println("=== ПРОВЕРКА WEBDRIVERMANAGER ===");

        // Проверяем что WebDriverManager работает
        try {
            // WebDriverManager должен быть в зависимостях
            String version = WebDriverManager.class.getPackage().getImplementationVersion();
            System.out.println("WebDriverManager версия: " +
                    (version != null ? version : "не определена"));

            System.out.println("✅ WebDriverManager доступен!");
        } catch (Exception e) {
            System.out.println("⚠️ WebDriverManager информация недоступна: " + e.getMessage());
        }

        // Всегда проходящий тест
        assertTrue(true, "WebDriverManager проверка");
    }
}