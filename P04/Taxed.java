public class Taxed extends Product {
    private static double tax;

    public Taxed(double cost, String name) {
        super(cost, name);
        this.tax = tax;
    }

    public static double setTaxRate(double salesTax) {
        return 1.0; // filler
    }

    @Override
    public double price() {
        return 3.0; // filler for now
    }
}
