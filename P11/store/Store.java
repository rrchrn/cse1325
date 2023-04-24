
package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Store implements Saveable {
    public Store(String name) {
        this.name = name;
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        save(bw, customers);
        save(bw, options);
        save(bw, computers);
        save(bw, orders);

    }

    private <T extends Saveable> void save(BufferedWriter bw, Set<T> set) throws IOException {
        bw.write(set.size() + "\n");
        for (var item : set) {
            item.save(bw);
        }
    }

    public Store(BufferedReader br) throws IOException {
        this.name = br.readLine();
    }

    public String name() {
        return this.name;
    }

    // ///////////////////////////////////////////////////////////
    // Customers

    public void add(Customer customer) {
        if (!customers.contains(customer))
            customers.add(customer);
    }

    public Object[] getCustomers() {
        return this.customers.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Options

    public void add(Option option) {
        if (!options.contains(option))
            options.add(option);
    }

    public Object[] getOptions() {
        return this.options.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Computers

    public void add(Computer computer) {
        if (!computers.contains(computer))
            computers.add(computer);
    }

    public Object[] getComputers() {
        return this.computers.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Orders

    public void add(Order order) {
        if (!orders.contains(order))
            orders.add(order);
    }

    public Object[] getOrders() {
        return this.orders.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Fields

    private String name;
    private TreeSet<Customer> customers = new TreeSet<>();
    private HashSet<Option> options = new HashSet<>();
    private HashSet<Computer> computers = new HashSet<Computer>();
    private HashSet<Order> orders = new HashSet<>();
}
