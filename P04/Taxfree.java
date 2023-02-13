public class Taxfree extends Product {

    public Taxfree(double cost, String name) {
        super(cost, name);
    }

    @Override
    public double price() {
        return 3.0; // filler for now
    }

}
