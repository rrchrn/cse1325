package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Computer {
    private String name;
    private String model;
    private ArrayList<Option> options = new ArrayList<>();
    private boolean deprecated = false; // new field
    private String imageFilename;

    public Computer(String name, String model, String imageFilename) {
        this.name = name;
        this.model = model;
        this.imageFilename = imageFilename;
    }

    public Computer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.model = br.readLine();
        this.imageFilename = br.readLine();
        int numOptions = Integer.parseInt(br.readLine());
        while (numOptions-- > 0)
            options.add(new Option(br));
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(model + '\n');
        bw.write(imageFilename + '\n');
        bw.write("" + options.size() + '\n');
        for (Option option : options)
            option.save(bw);

    }

    public void addOption(Option option) {
        options.add(option);
    }

    public long cost() {
        long cost = 0;
        for (Option o : options)
            cost += o.cost();
        return cost;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + " (" + model + ")");
        for (Option o : options)
            sb.append("\n  " + o);
        return sb.toString();
    }

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

    // new getter and setter for deprecation field
    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }
}
