package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir")
                    + "/src/test/resources/config.properties");

            properties = new Properties();
            properties.load(fis);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
