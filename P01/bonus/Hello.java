import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {
        Scanner userName = new Scanner(System.in);
        System.out.print("Enter your Name: ");

        String name = userName.nextLine();
        System.out.println("Hello " + name);

        userName.close();
    }
}
