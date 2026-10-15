package Client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import Server.InventoryInterface;

public class client {

    public static void main(String[] args) {

        try {

            // =========================================
            // SERVER IP ADDRESS
            // =========================================

            String SERVER_IP = "172.16.10.26";

            // Server ရဲ့ RMI Service ကို ချိတ်မယ်
            InventoryInterface inventory =
                    (InventoryInterface) Naming.lookup(
                            "rmi://" + SERVER_IP + ":1099/InventoryService"
                    );

            System.out.println("=================================");
            System.out.println("      Connected to Server");
            System.out.println("=================================");

            Scanner scanner = new Scanner(System.in);

            while (true) {

                System.out.println();
                System.out.println("========== INVENTORY ==========");
                System.out.println("1. Add Product");
                System.out.println("2. Update Product");
                System.out.println("3. Delete Product");
                System.out.println("4. Search Product");
                System.out.println("5. View Products");
                System.out.println("6. Sell Product");
                System.out.println("7. Total Items Sold");
                System.out.println("8. Exit");
                System.out.println("==============================");

                System.out.print("Choose option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                // =====================================
                // ADD PRODUCT
                // =====================================

                if (choice == 1) {

                    System.out.print("Product ID: ");
                    String productId = scanner.nextLine();

                    System.out.print("Product Name: ");
                    String productName = scanner.nextLine();

                    System.out.print("Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    inventory.addProduct(
                            productId,
                            productName,
                            quantity
                    );

                    System.out.println();
                    System.out.println("Product added successfully.");

                }

                // =====================================
                // UPDATE PRODUCT
                // =====================================

                else if (choice == 2) {

                    System.out.print("Product ID: ");
                    String productId = scanner.nextLine();

                    System.out.print("New Product Name: ");
                    String productName = scanner.nextLine();

                    System.out.print("New Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    inventory.updateProduct(
                            productId,
                            productName,
                            quantity
                    );

                    System.out.println();
                    System.out.println("Product updated.");

                }

                // =====================================
                // DELETE PRODUCT
                // =====================================

                else if (choice == 3) {

                    System.out.print("Product ID: ");
                    String productId = scanner.nextLine();

                    inventory.deleteProduct(productId);

                    System.out.println();
                    System.out.println("Product deleted.");

                }
             // =====================================
                // SEARCH PRODUCT
                // =====================================

                else if (choice == 4) {

                    System.out.print("Product ID: ");
                    String productId = scanner.nextLine();

                    String result =
                            inventory.searchProduct(productId);

                    System.out.println();
                    System.out.println("========== RESULT ==========");
                    System.out.println(result);
                    System.out.println("============================");

                }

                // =====================================
                // VIEW PRODUCTS
                // =====================================

                else if (choice == 5) {

                    String result =
                            inventory.viewProducts();

                    System.out.println();
                    System.out.println("======= ALL PRODUCTS =======");
                    System.out.println(result);
                    System.out.println("============================");

                }

                // =====================================
                // SELL PRODUCT
                // =====================================

                else if (choice == 6) {

                    System.out.print("Product ID: ");
                    String productId = scanner.nextLine();

                    System.out.print("Quantity to sell: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    String result =
                            inventory.sellProduct(
                                    productId,
                                    quantity
                            );

                    System.out.println();
                    System.out.println(result);

                }

                // =====================================
                // TOTAL ITEMS SOLD
                // =====================================

                else if (choice == 7) {

                    int total =
                            inventory.getTotalItemsSold();

                    System.out.println();
                    System.out.println(
                            "Total Items Sold: " + total
                    );

                }

                // =====================================
                // EXIT
                // =====================================

                else if (choice == 8) {

                    System.out.println(
                            "Client disconnected."
                    );

                    scanner.close();
                    break;

                }

                else {

                    System.out.println(
                            "Invalid option!"
                    );
                }
            }

        } catch (Exception e) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("       CONNECTION ERROR");
            System.out.println("=================================");

            e.printStackTrace();
        }
    }
}