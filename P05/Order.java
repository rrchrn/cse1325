import java.util.ArrayList;

public class Order {
    private static long nextOrderNumber = 0;
    private long orderNumber = nextOrderNumber++;

    private Customer customer;
    private ArrayList<Computer> computers = new ArrayList<Computer>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addComputer(Computer computer) {
        computers.add(computer);
    }

    @Override
    public String toString() {
        StringBuilder orderString = new StringBuilder();

        orderString.append("Order " + orderNumber + "for " + customer.getName() + " (" + customer.getEmail() + ")\n");
        for (Computer computer : computers) {
            orderString.append(computer.getName() + " (" + computer.getModel() + ")\n");
            for (Option option : computer.getOptions()) {
                // orderString.append(option.getName() + " (" + option.getCost() + ")\n");
                orderString.append(String.format("    %-12s ($%.2f)\n", option.getName(), option.getCost()));
            }

        }

        return orderString.toString();
    }

    @Override
    // compare customer field and each element of the computers ArrayList explicitly
    // but ignore orderNumber field
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Order)) {
            return false;
        }

        Order newOrder = (Order) o;
        return (this.customer.equals(newOrder.customer) && this.computers.equals(newOrder.computers));

    }
}