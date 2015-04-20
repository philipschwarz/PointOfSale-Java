package ca.jbrains.pos.test;

import java.util.Map;

/**
 * Created by pschwarz on 4/20/15.
 */
public class Catalogue
{
    private final Map<String, String> pricesByBarcode;

    Catalogue(Map<String, String> pricesByBarcode)
    {
        this.pricesByBarcode = pricesByBarcode;
    }

    public String findPrice(String barcode)
    {
        return pricesByBarcode.get(barcode);
    }
}
