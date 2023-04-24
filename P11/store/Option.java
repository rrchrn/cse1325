package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Option implements Saveable {
    protected String name;
    protected double cost;

    public Option(String name, double cost) {
        this.name = name;
        this.cost = cost;

        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write((int) cost + '\n');

    }

    public Option(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.cost = Long.parseLong(br.readLine());
    }

    // component that would og into computer

    public long cost() {
        return (long) (cost / 100.00);
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", ((double) cost / 100)) + ")\n";
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Option)) {
            return false;
        }
        Option otherObject = (Option) o;
        return this.name.equals(otherObject.name) && cost == otherObject.cost;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 7;
        hash = prime * hash + ((name == null) ? 0 : name.hashCode());
        hash = prime * hash + Double.hashCode(cost);
        return hash;
    }
}