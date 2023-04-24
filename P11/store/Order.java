package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Order implements Saveable {
    private static double nextOrderNumber = 0;
    private double orderNumber = nextOrderNumber++;

    private Customer customer;
    private ArrayList<Computer> computers = new ArrayList<Computer>();

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(Double.toString(orderNumber) + '\n');
        customer.save(bw);
        bw.write(Integer.toString(computers.size()) + '\n');
        for (Computer comp : computers) {
            comp.save(bw);
        }

    }

    public Order(BufferedReader br) throws IOException {

        this.orderNumber = Long.parseLong(br.readLine());
        this.customer = new Customer(br);
        int numComputers = Integer.parseInt(br.readLine());
        for (int i = 0; i < numComputers; i++) {
            Computer computer = new Computer(br);
            computers.add(computer);
        }
    }

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
                orderString.append(
                        String.format("    %-12s ($%.2f) ($%.2f\n", option.getName(), option.getCost(), cost()));
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

    public double cost() {
        double totalCost = 0.0;
        for (Computer computer : computers) {
            for (Option option : computer.getOptions()) {
                totalCost += option.getCost();
            }
        }
        return totalCost;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 7;
        hash = prime * hash + Double.hashCode(orderNumber);
        hash = prime * hash + ((customer == null) ? 0 : customer.hashCode());
        hash = prime * hash + ((computers == null) ? 0 : computers.hashCode());
        return hash;
    }
}