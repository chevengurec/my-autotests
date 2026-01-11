package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AlwaysPassTest {

    @Test
    public void testAlwaysPasses () {
        System.out.println("Этот тест всегда проходит");
        assertTrue(true, "Самое простое утверждение в мире");
    }

    @Test
    public void testTwoPlusTwo() {
        System.out.println("Проверка суммы: 2+2 = 4");
        assertEquals(4, 2 + 2, "Математика должна работать");
    }
}
