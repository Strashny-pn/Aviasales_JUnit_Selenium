package pasik.aviasales;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import static java.lang.Thread.sleep;
import static pasik.aviasales.Webdriver.TIME_OUT_CLICKABLE;

/**
 * Collection for WebElement
 */
public final class MetodsForElement {

    /**
     * Converts double to string.
     *
     * @return Textual representation of double.
     */


    public static void Goto() {
        Webdriver.getInstance();
    }

    public static void fillFieldById(String id, String text) {
        WebElement webElement = Webdriver.getWebdriver().findElement(By.xpath("//*[@id='" + id + "']"));
        waitWebElement(webElement);
        pasteTextField(webElement, text);
    }

    public static WebElement waitWebElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(Webdriver.getWebdriver(), TIME_OUT_CLICKABLE);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public static WebElement waitWebElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(Webdriver.getWebdriver(), TIME_OUT_CLICKABLE);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void pasteTextField(WebElement webElement, String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        webElement.clear();
        webElement.sendKeys(Keys.CONTROL + "v");
    }

    public static void setData(String data){
        if (data.equals("Текущая дата") ){

        }else if (data.contains("+")){

        }else{}
    }

    public static void closeChrome() {
        Webdriver.getWebdriver().close();
        Webdriver.getWebdriver().quit();
    }
}
