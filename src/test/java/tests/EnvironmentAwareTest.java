package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnvironmentAwareTest {

    @Test
    public void detectEnvironment() {
        // Получаем переменную окружения CI
        String ciEnvironment = System.getenv("CI");

        System.out.println("=== ТЕСТ: ОПРЕДЕЛЕНИЕ СРЕДЫ ===");
        System.out.println("Переменная окружения CI = " + ciEnvironment);

        if (ciEnvironment != null && ciEnvironment.equals("true")) {
            System.out.println("🎯 МЫ В CI СРЕДЕ!");
            System.out.println("GitHub Actions автоматически запустил этот тест");

            // В CI мы проверяем что переменная = "true"
            assertEquals("true", ciEnvironment, "В CI среде CI=true");

        } else {
            System.out.println("💻 МЫ ЛОКАЛЬНО (НЕ в CI)");
            System.out.println("Ты запустил тест вручную на своем компьютере");

            // Локально переменная CI либо null, либо не "true"
            assertTrue(ciEnvironment == null || !ciEnvironment.equals("true"));
        }

        System.out.println("✅ Тест завершен успешно в любой среде!");
    }

    @Test
    public void showSystemInfo() {
        System.out.println("=== СИСТЕМНАЯ ИНФОРМАЦИЯ ===");

        // Информация о Java
        String javaVersion = System.getProperty("java.version");
        String javaHome = System.getenv("JAVA_HOME");

        System.out.println("Версия Java: " + javaVersion);
        System.out.println("JAVA_HOME: " + (javaHome != null ? javaHome : "не установлен"));

        // Информация об ОС
        String osName = System.getProperty("os.name");
        String osArch = System.getProperty("os.arch");

        System.out.println("Операционная система: " + osName);
        System.out.println("Архитектура: " + osArch);

        // GitHub Actions переменные (если есть)
        String githubWorkflow = System.getenv("GITHUB_WORKFLOW");
        if (githubWorkflow != null) {
            System.out.println("GitHub Workflow: " + githubWorkflow);
        }

        // Простая проверка что информация доступна
        assertNotNull(javaVersion, "Версия Java должна быть доступна");
        assertNotNull(osName, "Имя ОС должно быть доступно");

        System.out.println("✅ Системная информация получена!");
    }

    @Test
    public void conditionalTest() {
        // Тест который ведет себя ПО-РАЗНОМУ в зависимости от среды

        boolean isCI = System.getenv("CI") != null
                && System.getenv("CI").equals("true");

        if (isCI) {
            System.out.println("🚀 В CI: запускаю упрощенную проверку");
            // В CI делаем простые быстрые проверки
            assertTrue(5 > 3, "Математика должна работать в CI");

        } else {
            System.out.println("🔍 Локально: запускаю подробную проверку");
            // Локально можем делать сложные проверки
            assertEquals(10, 5 + 5, "Подробная математика");
            assertNotEquals(0, 10, "10 не должно быть равно 0");
        }

        System.out.println("✅ Условный тест пройден!");
    }
}