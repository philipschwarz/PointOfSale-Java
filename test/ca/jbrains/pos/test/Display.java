package ca.jbrains.pos.test;

class Display
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
}
