package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Option {
    public Option(String name, long cost, String imageFilename) {
        if (cost < 0)
            throw new IllegalArgumentException("Negative cost for " + name + ": " + cost);
        this.name = name;
        this.cost = cost;
        this.imageFilename = imageFilename;
    }

    public Option(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.cost = Long.parseLong(br.readLine());
        this.imageFilename = br.readLine();
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write("" + Long.toString(cost) + '\n');
        bw.write(imageFilename + '\n');
    }

    public long cost() {
        return this.cost;
    }

    @Override
    public String toString() {
        long cents = cost % 100;
        return name + " ($" + cost / 100 + "." + ((cents < 10) ? "0" : "") + cents + ")";
    }

    public String getImageFilename() {
        return imageFilename;
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o)
                return true;
            if (this.getClass() != o.getClass())
                return false;
            Option p = (Option) o;
            return this.name.equals(p.name) && (this.cost == p.cost);
        } catch (Exception e) {
            return false;
        }
    }

    protected String name;
    protected long cost;
    private String imageFilename;
}
