package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    // 1. Статическое поле для хранения свойств
    private static Properties properties;

    // 2. Статический блок инициализации (выполняется ПРИ ЗАГРУЗКЕ КЛАССА)
    static {
        try {
            properties = new Properties(); // Создаём пустой объект Properties
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis); // Загружаем данные из файла
            fis.close(); // Закрываем поток
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить config.properties", e);
        }
    }

    // 3. Публичный метод для получения значений
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
