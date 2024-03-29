package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer {
    public Customer(String name, String email, String imageFilename) {
        int atIndex = email.indexOf('@', 0);
        int dotIndex = (atIndex >= 0) ? email.indexOf('@', 0) : -1;
        if (dotIndex < 0) // will be true if atIndex < 0, so don't check that
            throw new IllegalArgumentException("Invalid email address: " + email);
        this.name = name;
        this.email = email;
        this.imageFilename = imageFilename;
    }

    public Customer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();
        this.imageFilename = br.readLine();
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
        bw.write(imageFilename + '\n');
    }

    public String getImageFilename() {
        return imageFilename;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        try {
            if (this == o)
                return true;
            if (this.getClass() != o.getClass())
                return false;
            Customer c = (Customer) o;
            return this.name.equals(c.name) && this.email.equals(c.email);
        } catch (Exception e) {
            return false;
        }
    }

    private String name;
    private String email;
    private String imageFilename;

}
