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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static pasik.aviasales.Webdriver.TIME_OUT_CLICKABLE;

/**
 * Методы для работы с вебэлементами страницы
 */
public final class MetodsForElement {

    /**
     * Метод запускает chrome
     */
    public static void Goto() {
        Webdriver.getInstance();
    }


    /**
     * Метод ожидает когда элемент станет кликабельным, время ожидание храниься в "config.property"
     *
     * @param xpath - xpath до вебэлемента
     */
    public static WebElement waitWebElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(Webdriver.getWebdriver(), TIME_OUT_CLICKABLE);
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    /**
     * Метод ожидает когда элемент станет кликабельным, время ожидание храниься в "config.property"
     *
     * @param webElement - вебэлемент который должен стать кликабельным
     */
    public static WebElement waitWebElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(Webdriver.getWebdriver(), TIME_OUT_CLICKABLE);
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Метод вставляет в поле данные из буфера обмена (т.к. sendkeys отрабатывает не коректно из за
     * джава скрипта который висит на элементах)
     *
     * @param id   - id вебэлемента
     * @param text - текст которым заполняется поле
     */
    public static void fillFieldById(String id, String text) {
        WebElement webElement = Webdriver.getWebdriver().findElement(By.xpath("//*[@id='" + id + "']"));
        waitWebElement(webElement);
        pasteTextField(webElement, text);
    }

    /**
     * Метод вставляет в поле данные из буфера обмена (т.к. sendkeys отрабатывает не коректно из за
     * джава скрипта который висит на элементах)
     *
     * @param webElement - который нужно заполнить данными
     * @param text       - текст которым заполняется поле
     */
    public static void pasteTextField(WebElement webElement, String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        webElement.clear();
        webElement.sendKeys(Keys.CONTROL + "v");
    }

    /**
     * Метод заполняет каледарь требуемыми датами
     *
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
            int day = Integer.parseInt(data.replace("Текущая дата +", ""));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, day);
            String str = cal.getTime().toString();
            String currentDate = str.split(" ")[0] + " " + str.split(" ")[1] + " " + str.split(" ")[2] + " " + str.split(" ")[5];
            waitWebElement("//div[@aria-label='" + currentDate + "']").click();
        } else {
            waitWebElement("//div[@aria-label='" + data + "']").click();
        }
    }

    /**
     * Метод кликает на элемент который находит по xpath
     *
     * @param xpath - xpath по которому находиться элемент
     */
    public static void clickWebElementByXpath(String xpath) {
        Webdriver.getWebdriver().findElement(By.xpath(xpath)).click();
    }

    /**
     * Метод заполняет фильтр количество пассажиров взрослых детей и младенцев цикл for сделан до 10 потому,что нельзя
     * купить больше 9 билетов
     *
     * @param man      - количество взрослых
     * @param children - количество детей с 2 до 12 лет
     * @param baby     - количество младенцев
     */
    public static void setKolPassage(int man, int children, int baby) {
        List<String> manChildrenBaby = Arrays.asList("Взрослые", "Дети", "Младенцы");
        for (String k : manChildrenBaby) {
            int typePassage;
            if (k.equals("Взрослые")) {
                typePassage = man;
            } else if (k.equals("Дети")) {
                typePassage = children;
            } else {
                typePassage = baby;
            }
            for (int i = 0; i < 10; i++) {
                int col = Integer.parseInt(waitWebElement("//div[@class='additional-fields__passenger-title']/strong[text()='" + k + "']/../following-sibling::div//span").getText());
                if (col < typePassage) {
                    clickWebElementByXpath("//div[@class='additional-fields__passenger-title']/strong[text()='" + k + "']/../following-sibling::div//a[contains(@class, 'control --increment')]");
                } else if (col > typePassage) {
                    clickWebElementByXpath("//div[@class='additional-fields__passenger-title']/strong[text()='" + k + "']/../following-sibling::div//a[contains(@class, 'control --decrement')]");
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Метод ждет пока исчезнет элемент со страницы
     *
     * @param xpath - xpath до элемена который должен изчезнуть
     */
    public static Boolean invisibleElement(String xpath) {
        WebDriverWait wait = new WebDriverWait(Webdriver.getWebdriver(), TIME_OUT_CLICKABLE);
        return wait.until(ExpectedConditions.invisibilityOf(Webdriver.getWebdriver().findElement(By.xpath(xpath))));
    }

    /**
     * Метод кликает на элемент с помощью JS (Джава скрипт) т.к. нажать кнопку "Найти билеты" стандартными средствами
     * Selenium не удается
     *
     * @param xpath - xpath до элемента по которому надо кликнуть
     */
    public static void clickElementByJsByXpath(String xpath) {
        WebElement element = Webdriver.getWebdriver().findElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) Webdriver.getWebdriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public static boolean checkPriceTicket() {
        List<WebElement> webElementList = Webdriver.getWebdriver().findElements(By.xpath("//span[@class='buy-button__price']/span[@class='price --rub']"));
        boolean flag = true;
        int price = 0;
        for (WebElement element : webElementList) {
            if (price > Integer.parseInt(element.getText().replaceAll("[^\\p{Nd}]+", ""))) {
                flag = false;
            }
        }
        return flag;
    }


    /**
     * Метод закрывает chrome
     */
    public static void closeChrome() {
        Webdriver.getWebdriver().close();
        Webdriver.getWebdriver().quit();
    }
}
