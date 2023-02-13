public class Taxfree extends Product {

    public Taxfree(double cost, String name) {
        super(cost, name);
    }

    @Override
    public double price() {
        return cost; // filler for now
    }

}
