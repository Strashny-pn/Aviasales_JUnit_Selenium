package pasik.aviasales;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Webdriver {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config/config.properties";
    private static ChromeDriver webDriver = null;
    private static Webdriver instance;

    private Webdriver() {
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }

        System.setProperty("webdriver.chrome.driver", prop.get("chrome.driver.path").toString());

        webDriver = new ChromeDriver();
        webDriver.navigate().to(prop.get("webdriver.starting.url").toString());

    }

    public static Webdriver getInstance() {
        if (instance == null) {
            instance = new Webdriver();
        }
        return instance;
    }

    public static ChromeDriver getWebdriver() {
        return webDriver;
    }

}
