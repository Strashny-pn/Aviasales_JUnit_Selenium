package pasik.aviasales;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Webdriver {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config/config.properties";
    public static final long TIME_OUT_CLICKABLE = 50;

    private static ChromeDriver webDriver = null;
    private static Webdriver instance;

    /**
     * В общем делаем singleton и запускаем chrome c параметрами из "config.property"
     */
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

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        webDriver = new ChromeDriver();

        webDriver.navigate().to(prop.get("webdriver.starting.url").toString());
        webDriver.manage().window().maximize();
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
