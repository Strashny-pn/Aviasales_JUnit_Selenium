package pasik.aviasales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

/**
 * Collection of common String functions.
 */
public final class MetodsForElement {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/config/config.properties";

    /**
     * Converts double to string.
     *
     * @param source value to convert.
     * @return Textual representation of double.
     */
    public static String fromDouble(final double source) {
        return String.valueOf(source);
    }


    public static void Goto() {
        Webdriver.getInstance();
    }

    public static void fillFieldById(String id, String text) {
        Webdriver.getWebdriver().findElement(By.xpath("//*[@id='" + id + "']")).clear();
        Webdriver.getWebdriver().findElement(By.xpath("//*[@id='" + id + "']")).sendKeys(text);
    }

    public static void closeChrome() {
        Webdriver.getWebdriver().close();
        Webdriver.getWebdriver().quit();
    }
}
