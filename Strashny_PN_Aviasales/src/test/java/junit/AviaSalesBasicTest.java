package junit;

import org.junit.Assert;
import org.junit.Test;

import static pasik.aviasales.MetodsForElement.*;

public class AviaSalesBasicTest {

    @Test
    public void fillBasicData() {
    // Открываем Chrome данные берем из файла "config.property"
        Goto();

    //Заполняем поля пункт вылета и пункт прилета
        fillFieldById("origin", "Санкт-Петербург");
        fillFieldById("destination", "Екатеринбург");

    //Выставляем дату вылета и дату возвращения
        waitWebElement("//label[@class='price-switcher__label' and text()='Показать цены в одну сторону']");
        // устанавливаем текущую дату
        setData("Текущая дата +2");
        waitWebElement("//div[@class='return-clear' and text()='Обратный билет не нужен']");
        // устанавливаем текущую дату плюс 10 дней
        setData("Текущая дата +10");

    //Заполняем поля количество пассажиров взрослые дети и младенцы
        clickWebElementByXpath("//div[@class='of_main_form__additional-fields']");
        waitWebElement("//div[@class='custom-radio__caption' and text()='Эконом']");
        setKolPassage(2, 2, 0);
        clickWebElementByXpath("//div[@class='of_main_form__additional-fields']");

    //Выставляем чекбокс не отрывать окно с гостиницами
        invisibleElement("//div[@class='custom-radio__caption' and text()='Эконом']");
        clickWebElementByXpath("//input[@id='clicktripz']/..");

    // Нажимаем кнопку "Найти билеты"
        clickElementByJsByXpath("//span[text()='Найти билеты']/..");


    // Ожидаем загрузки данных
        waitWebElement("//div[contains(@class,'loader__stripes --animation-finished')]");

    // Заполняем фильты поиска билетов
        clickWebElementByXpath("//label[@class='checkboxes-list__label' and text()='Все']");
        clickWebElementByXpath("//label[@class='checkboxes-list__label' and text()='Без пересадок']");

        clickWebElementByXpath("//div[@class='filter__header' and text()='Багаж']");
        clickWebElementByXpath("//div[@class='filter__header' and text()='Багаж']/..//label[@class='checkboxes-list__label' and text()='Все']");
        clickWebElementByXpath("//div[@class='filter__header' and text()='Багаж']/..//label[@class='checkboxes-list__label' and text()='Багаж и ручная кладь']");

        Assert.assertTrue("Найденые билеты отсортированы в неправильном порядке", checkPriceTicket());

        closeChrome();
    }
}
