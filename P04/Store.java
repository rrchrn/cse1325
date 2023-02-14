import java.util.ArrayList;

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

        System.out.println("=====================");
        System.out.println("Welcome to the Store");
        System.out.println("=====================\n");

        for (Product item : products) {
            System.out.println(item);
        }

    }
}