package store;

import java.util.ArrayList;
import java.util.Set;

import gui.MainWin.Record;

import java.util.HashSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Store {
    public Store(String name) {
        this.name = name;
    }

    public Store(BufferedReader br) throws IOException {
        this.name = br.readLine();
        int numOptions = Integer.parseInt(br.readLine());
        while (numOptions-- > 0)
            customers.add(new Customer(br));

        numOptions = Integer.parseInt(br.readLine());
        while (numOptions-- > 0)
            options.add(new Option(br));

        numOptions = Integer.parseInt(br.readLine());
        while (numOptions-- > 0)
            computers.add(new Computer(br));

        numOptions = Integer.parseInt(br.readLine());
        while (numOptions-- > 0)
            orders.add(new Order(br));
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');

        bw.write("" + customers.size() + '\n');
        for (Customer customer : customers)
            customer.save(bw);

        bw.write("" + options.size() + '\n');
        for (Option option : options)
            option.save(bw);

        bw.write("" + computers.size() + '\n');
        for (Computer computer : computers)
            computer.save(bw);

        bw.write("" + orders.size() + '\n');
        for (Order order : orders)
            order.save(bw);

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

    public Object[] customers() {
        return this.customers.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Options

    public void add(Option option) {
        if (!options.contains(option))
            options.add(option);
    }

    public Object[] options() {
        return this.options.toArray();
    }

    // ///////////////////////////////////////////////////////////
    // Computers

    public void add(Computer computer) {
        if (!computers.contains(computer))
            computers.add(computer);
    }

    public Computer[] computers() {
        Set<Computer> nonDeprecatedComputers = new HashSet<>();
        for (Computer computer : this.computers) {
            if (!computer.isDeprecated()) {
                nonDeprecatedComputers.add(computer);
            }
        }
        return nonDeprecatedComputers.toArray(new Computer[0]);
    }

    // ///////////////////////////////////////////////////////////
    // Orders

    public void add(Order order) {
        if (!orders.contains(order))
            orders.add(order);
    }

    public Object[] orders() {
        return this.orders.toArray();
    }

    /// ????? for record
    public String getRecordDetails(Record recordType, String recordName) {
        switch (recordType) {
            case CUSTOMER:
                for (Customer customer : customers) {
                    if (customer.getName().equals(recordName)) {
                        return customer.toString();
                    }
                }
                break;
            case OPTION:
                for (Option option : options) {
                    if (option.getName().equals(recordName)) {
                        return option.toString();
                    }
                }
                break;
            case COMPUTER:
                for (Computer computer : computers) {
                    if (computer.getName().equals(recordName)) {
                        return computer.toString();
                    }
                }
                break;
        }
        return null;
    }

    public String[] listNames(Record recordType) {
        switch (recordType) {
            case CUSTOMER:
                String[] customerNames = new String[customers.size()];
                for (int i = 0; i < customers.size(); i++) {
                    customerNames[i] = customers.get(i).getName();
                }
                return customerNames;
            case OPTION:
                String[] optionNames = new String[options.size()];
                for (int i = 0; i < options.size(); i++) {
                    optionNames[i] = options.get(i).getName();
                }
                return optionNames;
            case COMPUTER:
                String[] computerNames = new String[computers.size()];
                for (int i = 0; i < computers.size(); i++) {
                    computerNames[i] = computers.get(i).getName();
                }
                return computerNames;
        }
        return new String[0];
    }

    // ///////////////////////////////////////////////////////////
    // Fields

    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Option> options = new ArrayList<>();
    private ArrayList<Computer> computers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

}
