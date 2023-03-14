package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.regex.*;

public class Customer {
    String name;
    private String email;

    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(this.getEmail() + '\n');

    }

    public Customer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();

    }

    public Customer(String name, String email) {
        this.email = email;
        this.name = name;

        if (validateEmail(email) != 1) {
            throw new IllegalArgumentException("Invalid Email Syntax ex. name@domain.com");
        }

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;

    }

    public static final Pattern regexEmail = Pattern.compile("^([a-zA-Z0-9_.-]+)@([a-zA-Z0-9_.-]+).([a-zA-Z]{2,5})$",
            Pattern.CASE_INSENSITIVE);

    public static int validateEmail(String email) {
        //
        Matcher matchedPattern = regexEmail.matcher(email);
        boolean isValid = matchedPattern.matches();
        if (isValid) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {
        return name + " (" + email + ")\n";

    }

    // to make sure there's not multiple of same email??
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) o;
        return this.email.equals(otherCustomer.email);
    }
}