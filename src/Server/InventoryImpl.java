package Server;
import java.rmi.RemoteException; import java.rmi.server.UnicastRemoteObject; import java.util.HashMap; import java.util.Map;
public class InventoryImpl extends UnicastRemoteObject implements InventoryInterface {
private static final long serialVersionUID = 1L;

private int totalItemsSold = 0;
private final Map<String, String> products = new HashMap<>();

public InventoryImpl() throws RemoteException {
    
    super(1099); 
}

@Override
public synchronized void addProduct(String productId, String productName, int quantity) throws RemoteException {
    products.put(productId, productName + " - Quantity: " + quantity);

    System.out.println("Product added successfully");
    System.out.println("Product ID: " + productId);
    System.out.println("Product Name: " + productName);
    System.out.println("Quantity: " + quantity);
    System.out.println(viewProducts());
}

@Override
public synchronized void updateProduct(String productId, String productName, int quantity) throws RemoteException {
    if (products.containsKey(productId)) {
        products.put(productId, productName + " - Quantity: " + quantity);
        System.out.println("Product updated: " + productId);
    } else {
        System.out.println("Update failed: Product not found: " + productId);
    }
}

@Override
public synchronized void deleteProduct(String productId) throws RemoteException {
    if (products.remove(productId) != null) {
        System.out.println("Product deleted: " + productId);
    } else {
        System.out.println("Delete failed: Product not found: " + productId);
    }
}

@Override
public synchronized String searchProduct(String productId) throws RemoteException {
    if (products.containsKey(productId)) {
        return "Product ID: " + productId + "\n" + products.get(productId);
    }
    return "Product not found.";
}

@Override
public synchronized String viewProducts() throws RemoteException {
    if (products.isEmpty()) {
        return "No products available.";
    }

    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, String> entry : products.entrySet()) {
        result.append("Product ID: ")
              .append(entry.getKey())
              .append(" | ")
              .append(entry.getValue())
              .append("\n");
    }
    return result.toString();
}

@Override
public synchronized String sellProduct(String productId, int quantity) throws RemoteException {
    if (!products.containsKey(productId)) {
        return "Sale failed!\nProduct not found.";
    }

    if (quantity <= 0) {
        return "Sale failed!\nInvalid quantity.";
    }

    String product = products.get(productId);
    String productName = product.split(" - Quantity:")[0];
    int currentQuantity = Integer.parseInt(product.split("Quantity: ")[1]);

    if (quantity > currentQuantity) {
        return "Sale failed!\n"
             + "Not enough stock.\n"
             + "Available stock: " + currentQuantity + "\n"
             + "Requested quantity: " + quantity;
    }

    int remainingQuantity = currentQuantity - quantity;
    products.put(productId, productName + " - Quantity: " + remainingQuantity);
    totalItemsSold += quantity;

    System.out.println("Product sold: " + productId);
    System.out.println("Sold quantity: " + quantity);
    System.out.println("Remaining quantity: " + remainingQuantity);

    return "Product sold successfully!\n" + "Remaining stock: " + remainingQuantity;
}

@Override
public synchronized int getTotalItemsSold() throws RemoteException {
    return totalItemsSold;
}
}