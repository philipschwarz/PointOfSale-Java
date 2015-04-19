package ca.jbrains.pos.test;

class Sale
{
    private final SellOneItemTest.Catalogue catalogue;
    private final Display display;

    public Sale(SellOneItemTest.Catalogue catalogue, Display display)
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

        String priceAsText = catalogue.findPrice(barcode);
        if (priceAsText == null)
        {
            display.displayProductNotFoundMessage(barcode);
        } else
        {
            display.displayPrice(priceAsText);
        }
    }

}
