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

    /// Professor Rice Suggested Solution
    @Override
    public boolean equals(Object o) {
        try {
            if (this == o)
                return true;
            if (this.getClass() != o.getClass())
                return false;
            Computer c = (Computer) o;
            return this.toString().equals(c.toString());
        } catch (Exception e) {
            return false;
        }
    }
}
