package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CIVerificationTest {

    @Test
    public void verifyCIEnvironment() {
        System.out.println("=== CI Verification Test ===");

        // Проверяем, что тест запускается
        assertTrue(true, "Basic test should pass");

        // Выводим информацию о среде
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));

        boolean isCI = System.getenv("CI") != null;
        System.out.println("Running in CI: " + isCI);

        if (isCI) {
            System.out.println("✅ SUCCESS: Tests are running in GitHub Actions!");
        } else {
            System.out.println("ℹ️ Running locally");
        }
    }

    @Test
    public void simpleMathTest() {
        // Еще один простой тест для статистики
        assertEquals(4, 2 + 2, "Basic math should work");
    }
}