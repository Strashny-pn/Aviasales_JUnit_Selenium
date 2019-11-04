package pasik.aviasales;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Webdriver {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/config/config.properties";
    public static final long TIME_OUT_CLICKABLE = 50;

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
