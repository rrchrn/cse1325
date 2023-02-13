abstract class Product {
    protected double cost;
    protected String name;

    public Product(double cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public abstract double price();

    @Override
    public String toString() {

        return name + "(" + String.format("%,.2f", cost) + ") " + price(); // filler

    }

}