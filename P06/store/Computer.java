package store;

import java.util.ArrayList;

public class Computer {
    private String name;
    private String model;
    private ArrayList<Option> options = new ArrayList<Option>();

    public Computer(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public void addOption(Option option) {
        options.add(option);

    }

    public long cost() {
        long totalCost = 0;
        for (Option i : options) {
            totalCost = totalCost + i.getCost();
        }
        return totalCost;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        // have to use string builder????
        StringBuilder finalString = new StringBuilder();
        // option will have cost and name
        finalString.append(name + " (" + model + ")\n");
        for (Option i : options) {
            finalString.append(i.getName() + " (" + i.getCost() + ")\n");
        }

        return finalString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this.toString().equals(o.toString())) {
            return true;
        }

        return false;
    }
}