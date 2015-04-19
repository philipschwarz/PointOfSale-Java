package ca.jbrains.pos.test;

import junit.framework.Assert;
import org.junit.Test;

import java.awt.*;

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
}
