public class Option {
    protected String name;
    protected long cost;

    // component that would og into computer
    public Option(String name, long cost) {
        this.name = name;
        this.cost = cost;

        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }

    }

    public long cost() {
        return (long) (cost / 100.0);
    }

    @Override
    public String toString() {
        return name + " (" + cost + ")\n";

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}