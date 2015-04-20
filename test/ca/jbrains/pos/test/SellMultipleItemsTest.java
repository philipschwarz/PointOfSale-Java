package ca.jbrains.pos.test;

import junit.framework.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.Collections;

public class SellMultipleItemsTest
{
    @Test
    public void zeroItems() throws Exception
    {
        Display display = new Display();
        Sale sale = new Sale(null, display);

        sale.onTotal();

        Assert.assertEquals("No sale in progress. Try scanning a product.", display.getText());
    }

    @Test
    public void oneItemFound() throws Exception
    {
        Catalogue catalogue = new Catalogue(Collections.singletonMap("123456", "$6.50"));
        Display display = new Display();
        Sale sale = new Sale(catalogue, display);
        sale.onBarcode("123456");

        sale.onTotal();

        Assert.assertEquals("$6.50", display.getText());
    }
}
