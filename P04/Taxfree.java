public class Taxfree extends Product {

    public Taxfree(double cost, String name) {
        super(cost, name);
    }

    @Override
    public double price() {
        return cost; // filler for now
    }

    public static class Bagels extends Taxfree {
        public Bagels() {
            super(4.18, "Bagels");
        }
    }

    public static class Milk extends Taxfree {
        public Milk() {
            super(2.93, "Milk");
        }
    }

    public static class Yogurt extends Taxfree {
        public Yogurt() {
            super(3.54, "Yogurt");
        }
    }
}
