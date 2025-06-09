
import java.util.*;

import kass_java.service.ProductService;

public class MainCLI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService service = new ProductService();

        while (true) {
            System.out.println("\n=====Product management=====");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. View all products");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // newline залгих
        }
           
    }
}
