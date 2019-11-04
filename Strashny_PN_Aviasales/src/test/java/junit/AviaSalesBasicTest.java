package junit;

import com.sun.org.glassfish.gmbal.NameValue;
import org.junit.Test;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
import static pasik.aviasales.MetodsForElement.*;

public class AviaSalesBasicTest {

    @Test
    public void fillBasicData() {
        Goto();
        fillFieldById("origin", "Санкт – Петербург");
        fillFieldById("destination", "Екатеринбург");
        waitWebElement("//label[@class='price-switcher__label' and text()='Показать цены в одну сторону']");
        setData("Текущая дата");
        waitWebElement("//div[@class='return-clear' and text()='Обратный билет не нужен']");
        setData("Текущая дата +10");
        clickWebElementByXpath("//div[@class='of_main_form__additional-fields']");

        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        closeChrome();
    }
}
