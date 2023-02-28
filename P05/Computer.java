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
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}