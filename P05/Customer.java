import java.util.regex.*;

public class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.email = email;
        this.name = name;

        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Invalid Email Syntax ex. name@domain.com");
        }

    }

    public static final Pattern regexEmail = Pattern.compile("^([a-zA-Z0-9_-.]+)@([a-zA-Z0-9_-.]+).([a-zA-Z]{2,5})$",
            Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String email) {
        Matcher matchedPattern = regexEmail.matcher(email);
        return matchedPattern.matches();
    }

    @Override
    public String toString() {
        return name + " (" + email + ")\n";

    }

    // to make sure there's not multiple of same email??
    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            Customer otherEmail = (Customer) o;
            return this.email.equals(otherEmail.email);
        }
        return false;
    }
}