package ca.jbrains.pos.test;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SellOneItemTest
{

    private Display display;
    private Sale sale;

    @Before
    public void setUp() throws Exception
    {
        display = new Display();
        sale = new Sale(new Catalogue(new HashMap<String, String>()
        {{
                put("12345", "$7.95");
                put("23456", "$12.50");
            }}), display);
    }

    @Test
    public void productFound() throws Exception
    {
        sale.onBarcode("12345");
        assertEquals("$7.95", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception
    {
        sale.onBarcode("23456");
        assertEquals("$12.50", display.getText());
    }

    @Test
    public void productNotFound() throws Exception
    {
        sale.onBarcode("99999");
        assertEquals("Product not found for 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception
    {
        final Display display = new Display();
        final Sale sale = new Sale(new Catalogue(null), display);

        sale.onBarcode("");
        assertEquals("Scan error: empty barcode", display.getText());
    }

    public static class Catalogue
    {
        private final Map<String, String> pricesByBarcode;

        private Catalogue(Map<String, String> pricesByBarcode)
        {
            this.pricesByBarcode = pricesByBarcode;
        }

        public String findPrice(String barcode)
        {
            return pricesByBarcode.get(barcode);
        }
    }
}
