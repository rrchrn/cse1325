package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Option {
    protected String name;
    protected long cost;

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write((int) cost + '\n');

    }

    public Option(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.cost = Long.parseLong(br.readLine());
    }

    // component that would og into computer
    public Option(String name, long cost) {
        this.name = name;
        this.cost = cost;

        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

    }

    public long cost() {
        return (long) (cost / 100.00);
    }

    public String getName() {
        return name;
    }

    public long getCost() {
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

}