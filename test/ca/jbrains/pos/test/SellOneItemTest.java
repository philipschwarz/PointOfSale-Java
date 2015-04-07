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
        sale = new Sale(display, new HashMap<String, String>()
        {{
                put("12345", "$7.95");
                put("23456", "$12.50");
            }});
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
        final Sale sale = new Sale(display, null);

        sale.onBarcode("");
        assertEquals("Scan error: empty barcode", display.getText());
    }

    private static class Display
    {
        private String text;

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }
    }

    private static class Sale
    {
        private Display display;
        private final Map<String, String> pricesByBarcode;

        private Sale(Display display, Map<String, String> pricesByBarcode)
        {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }

        public void onBarcode(String barcode)
        {
            // SMELL Refused bequest; move this up the call stack?
            if ("".equals(barcode))
            {
                displayEmptyBarcodeMessage();
                return;
            }

            String priceAsText = findPrice(barcode);
            if (priceAsText == null)
            {
                displayProductNotFoundMessage(barcode);
            }
            else
            {
                displayPrice(priceAsText);
            }
        }

        private void displayPrice(String priceAsText)
        {
            display.setText(priceAsText);
        }

        private String findPrice(String barcode)
        {
            return pricesByBarcode.get(barcode);
        }

        private void displayProductNotFoundMessage(String barcode)
        {
            display.setText("Product not found for " + barcode);
        }

        private void displayEmptyBarcodeMessage()
        {
            display.setText("Scan error: empty barcode");
        }
    }
}
