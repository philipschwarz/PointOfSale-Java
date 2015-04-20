package ca.jbrains.pos.test;

class Sale
{
    private final Catalogue catalogue;
    private final Display display;
    private String priceAsText;

    public Sale(Catalogue catalogue, Display display)
    {
        this.catalogue = catalogue;
        this.display = display;
    }

    public void onBarcode(String barcode)
    {
        // SMELL Refused bequest; move this up the call stack?
        if ("".equals(barcode))
        {
            display.displayEmptyBarcodeMessage();
            return;
        }

        priceAsText = catalogue.findPrice(barcode);
        if (priceAsText == null)
        {
            display.displayProductNotFoundMessage(barcode);
        }
        else
        {
            display.displayPrice(priceAsText);
        }
    }

    public void onTotal()
    {
        if (priceAsText == null)
        {
            display.displayNoSaleInProgressMessage(this);
        }
        else
        {
            display.text = "Total: $6.50";
        }
    }
}
