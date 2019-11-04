package junit;

import org.junit.Test;
import pasik.aviasales.Webdriver;

import static java.lang.Thread.sleep;
import static pasik.aviasales.MetodsForElement.*;

public class AviaSalesBasicTest {

    @Test
    public void fillBasicData() {
        Goto();
        fillFieldById("origin", "Санкт-Петербург");
        fillFieldById("destination", "Екатеринбург");
        waitWebElement("//label[@class='price-switcher__label' and text()='Показать цены в одну сторону']");
        setData("Текущая дата");
        waitWebElement("//div[@class='return-clear' and text()='Обратный билет не нужен']");
        setData("Текущая дата +10");
        clickWebElementByXpath("//div[@class='of_main_form__additional-fields']");
        waitWebElement("//div[@class='custom-radio__caption' and text()='Эконом']");
        setKolPassage(2, 2, 0);
        clickWebElementByXpath("//div[@class='of_main_form__additional-fields']");
        invisibleElement("//div[@class='custom-radio__caption' and text()='Эконом']");
        clickWebElementByXpath("//input[@id='clicktripz']/..");
        clickElementByJsByXpath("//span[text()='Найти билеты']/..");
        waitWebElement("//div[contains(@class,'loader__stripes --animation-finished')]");
        clickWebElementByXpath("//label[@class='checkboxes-list__label' and text()='Все']");
        clickWebElementByXpath("//label[@class='checkboxes-list__label' and text()='Без пересадок']");

        clickWebElementByXpath("//div[@class='filter__header' and text()='Багаж']");
        clickWebElementByXpath("//div[@class='filter__header' and text()='Багаж']/..//label[@class='checkboxes-list__label' and text()='Все']");
        clickWebElementByXpath("//div[@class='filter__header' and text()='Багаж']/..//label[@class='checkboxes-list__label' and text()='Багаж и ручная кладь']");


        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        closeChrome();
    }
}
