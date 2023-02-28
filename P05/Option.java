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

    public String getName() {
        return name;
    }

    public long getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (" + cost + ")\n";
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