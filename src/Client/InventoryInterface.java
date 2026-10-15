package Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InventoryInterface extends Remote {

    void addProduct(
            String productId,
            String productName,
            int quantity
    ) throws RemoteException;

    void updateProduct(
            String productId,
            String productName,
            int quantity
    ) throws RemoteException;

    void deleteProduct(
            String productId
    ) throws RemoteException;

    String searchProduct(
            String productId
    ) throws RemoteException;

    String viewProducts()
            throws RemoteException;

    String sellProduct(
            String productId,
            int quantity
    ) throws RemoteException;

    int getTotalItemsSold()
            throws RemoteException;
}