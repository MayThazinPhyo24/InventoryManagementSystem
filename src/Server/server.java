package Server;
import java.rmi.Naming; import java.rmi.registry.LocateRegistry;
public class server {
public static void main(String[] args) {
    try {
        System.setProperty(
                "java.rmi.server.hostname",
                "172.20.10.6");

        LocateRegistry.createRegistry(1099);

        InventoryImpl inventory = new InventoryImpl();

        Naming.rebind("InventoryService", inventory);

        System.out.println("Inventory Server is running...");
        System.out.println("Server IP: 172.20.10.6                                                     ");
        System.out.println("Port: 1099");
        System.out.println("Waiting for clients...");

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}