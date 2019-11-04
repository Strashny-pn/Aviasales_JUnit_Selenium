package pasik.aviasales;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Calendar;

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

    /**
     * @param data - переменная типа текст. Текст может быть трех типов
     *             1: Переменая может содержать значение "Текущая дата" - выставит текущую дату.
     *             2: Переменная может содержать значение "Текущая дата + 7" - выставить дату познее текущей на 7 дней
     *             3: Переменная может содержать знаение любой даты в формате - "Mon Nov 04 2019"
     */
    public static void setData(String data) {
        if (data.equals("Текущая дата")) {
            Calendar cal = Calendar.getInstance();
            String str = cal.getTime().toString();
            String currentDate = str.split(" ")[0] + " " + str.split(" ")[1] + " " + str.split(" ")[2] + " " + str.split(" ")[5];
            waitWebElement("//div[@aria-label='" + currentDate + "']").click();
        } else if (data.contains("Текущая дата +")) {
            int day = Integer.parseInt(data.replace("Текущая дата +",""));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, day);
            String str = cal.getTime().toString();
            String currentDate = str.split(" ")[0] + " " + str.split(" ")[1] + " " + str.split(" ")[2] + " " + str.split(" ")[5];
            waitWebElement("//div[@aria-label='" + currentDate + "']").click();
        } else {
            waitWebElement("//div[@aria-label='" + data + "']").click();
        }
    }

    public static void clickWebElementByXpath(String xpath) {
        Webdriver.getWebdriver().findElement(By.xpath(xpath)).click();
    }
    public static void setKolvoPassage(int man, int children, int baby) {

    }

    //div[@class='additional-fields__passenger-title']/strong[text()='Взрослые']/../following-sibling::div//span
    //div[@class='additional-fields__passenger-title']/strong[text()='Взрослые']/../following-sibling::div//a[contains(@class, 'control --decrement')]
    //div[@class='additional-fields__passenger-title']/strong[text()='Взрослые']/../following-sibling::div//a[contains(@class, 'control --increment')]

    //div[@class='additional-fields__passenger-title']/strong[text()='Дети']/../following-sibling::div//span
    //div[@class='additional-fields__passenger-title']/strong[text()='Дети']/../following-sibling::div//a[@class='additional-fields__passenger-control --decrement']
    //div[@class='additional-fields__passenger-title']/strong[text()='Дети']/../following-sibling::div//a[@class='additional-fields__passenger-control --increment']

    //div[@class='additional-fields__passenger-title']/strong[text()='Младенцы']/../following-sibling::div//span
    //div[@class='additional-fields__passenger-title']/strong[text()='Младенцы']/../following-sibling::div//a[contains(@class, 'control --decrement')]
    //div[@class='additional-fields__passenger-title']/strong[text()='Младенцы']/../following-sibling::div//a[contains(@class, 'control --increment')]


    public static void closeChrome() {
        Webdriver.getWebdriver().close();
        Webdriver.getWebdriver().quit();
    }
}
