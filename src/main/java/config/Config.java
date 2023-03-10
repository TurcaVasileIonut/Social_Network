package config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String CONFIG_LOCATION = "D:\\JAVA\\Social_Network\\src\\main\\java\\config\\config.properties";
    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(CONFIG_LOCATION));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config properties");
        }
    }
}