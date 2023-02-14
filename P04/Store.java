import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private static ArrayList<Product> products = new ArrayList<Product>();
    private static ArrayList<Product> shoppingCart = new ArrayList<Product>();

    public static void main(String[] args) {

        Taxed.setTaxRate(.0625);

        // Taxable - ice, candy, lotion
        Taxed.Ice ice = new Taxed.Ice();
        Taxed.Candy candy = new Taxed.Candy();
        Taxed.Lotion lotion = new Taxed.Lotion();
        products.add(ice);
        products.add(candy);
        products.add(lotion);

        // Non-Taxable - Bagel, Milk, Yogurt
        Taxfree.Bagels bagels = new Taxfree.Bagels();
        Taxfree.Milk milk = new Taxfree.Milk();
        Taxfree.Yogurt yogurt = new Taxfree.Yogurt();
        products.add(bagels);
        products.add(milk);
        products.add(yogurt);

        // Add scanner for input
        int status = 1;
        int answer = 0;
        Scanner input = new Scanner(System.in);

        try {
            while (status == 1) {
                System.out.println("\n=====================");
                System.out.println("Welcome to the Store");
                System.out.println("=====================\n");

                int count = 0;
                // Products
                for (Product item : products) {
                    System.out.print(count + " )  ");
                    System.out.println(item);
                    count = count + 1;
                }

                System.out.println("\nCurrent Order");
                System.out.println("-------------");

                // Shopping Cart
                double totalCost = 0;

                for (Product addedItem : shoppingCart) {
                    System.out.println(addedItem);
                    totalCost = totalCost + addedItem.cost;
                }

                // Print total price
                System.out.println(String.format("Total price: %.2f", totalCost));

                System.out.println("\nBuy which product?");
                answer = input.nextInt();

                if (answer < 0 || answer > products.size()) {
                    throw new IllegalArgumentException("Please choose number within bounds");
                }

                shoppingCart.add(products.get(answer));
            }
            input.close();

        } catch (Exception e) {
            System.err.println("Invalid Input!");
            System.exit(-1);
        }

    }

}