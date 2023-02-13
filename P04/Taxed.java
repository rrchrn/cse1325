public class Taxed extends Product {
    private static double tax;
    public static double salesTaxRate = 0.0;

    public Taxed(double cost, String name) {
        super(cost, name);
    }

    public static double setTaxRate(double salesTax) {
        salesTax = salesTaxRate;
        return salesTaxRate;
    }

    @Override
    public double price() {
        return 3.0; // filler for now
    }
}
