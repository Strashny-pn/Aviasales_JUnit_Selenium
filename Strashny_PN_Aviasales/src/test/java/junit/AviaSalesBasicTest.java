package junit;

import org.junit.Test;
import pasik.aviasales.MetodsForElement;

import static java.lang.Thread.sleep;
import static pasik.aviasales.MetodsForElement.*;

public class AviaSalesBasicTest {

    @Test
    public void fillBasicData() {
        Goto();
        fillFieldById("origin", "Санкт – Петербург");
        fillFieldById("destination", "Екатеринбург");
        waitWebElement("//div[@class='datefield-dropdown__header' and text()='Дата вылета']");
        try {
            sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        closeChrome();
    }
}
