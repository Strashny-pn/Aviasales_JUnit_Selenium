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
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closeChrome();
    }


//    @Test
//    public void testToDouble() {
//        assertEquals(3.1415, StringUtils.toDouble("3.1415"), 0.0001);
//        assertEquals("Not NaN for null", Double.NaN, StringUtils.toDouble(null), 0.00001);
//    }
//
//    @Test
//    public void testFromDouble() {
//        double source = 3.1415;
//        String expected="3.1415";
//
//        String actual = StringUtils.fromDouble(source);
//        assertEquals("Unexpected string value", expected, actual);
//    }
}
