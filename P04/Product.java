abstract class Product {
    protected double cost;
    protected String name;

    public Product(double cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public Product(String name, double cost) {

    }

    public abstract double price();

    @Override
    public String toString() {

    }

}