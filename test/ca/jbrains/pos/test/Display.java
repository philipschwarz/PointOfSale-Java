package ca.jbrains.pos.test;

class Display
{
    public String text;

    public String getText()
    {
        return text;
    }

    private void setText(String text)
    {
        this.text = text;
    }

    public void displayPrice(String priceAsText)
    {
        setText(priceAsText);
    }

    public void displayProductNotFoundMessage(String barcode)
    {
        setText("Product not found for " + barcode);
    }

    public void displayEmptyBarcodeMessage()
    {
        setText("Scan error: empty barcode");
    }

    public void displayNoSaleInProgressMessage(Sale sale)
    {
        setText("No sale in progress. Try scanning a product.");
    }
}
