import java.util.ArrayList;

public class Order {
    private static long nextOrderNumber = 0;
    private long orderNumber;

    private Customer customer;
    private ArrayList<Computer> computers = new ArrayList<Computer>();
}