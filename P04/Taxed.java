public class Taxed extends Product {
    private static double tax;
    public static double salesTaxRate = 0.0;

    public Taxed(double cost, String name) {
        super(cost, name);
    }

    public static double setTaxRate(double salesTax) {
        salesTaxRate = salesTax;
        return salesTaxRate;
    }

    @Override
    public double price() {
        return cost * (1 + salesTaxRate); // filler for now
    }

    public static class Ice extends Taxed {
        public Ice() {
            super(.95, "Ice");
        }
    }

    public static class Candy extends Taxed {
        public Candy() {
            super(1.35, "Candy");
        }
    }

    public static class Lotion extends Taxed {
        public Lotion() {
            super(7.99, "");
        }
    }
}
