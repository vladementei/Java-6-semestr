package service;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteService extends Remote {
    Product getProductById(int id) throws RemoteException;

    List<Product> getALLProducts() throws RemoteException;

    Product insertProduct(Product product) throws RemoteException;

    Product updateProduct(Product product) throws RemoteException;

    void deleteProduct(Product product) throws RemoteException;

    Warehouse getWarehouseById(int id) throws RemoteException;

    List<Warehouse> getALLWarehouses() throws RemoteException;

    Warehouse insertWarehouse(Warehouse warehouse) throws RemoteException;

    Warehouse updateWarehouse(Warehouse warehouse) throws RemoteException;

    void deleteWarehouse(Warehouse warehouse) throws RemoteException;

    int getProductAmount(Product product) throws RemoteException;

    List<Remnant> getAllByProduct(Product product) throws RemoteException;

    List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RemoteException;

    Remnant insert(Remnant remnant) throws RemoteException;

    Remnant update(Remnant remnant) throws RemoteException;

    void delete(Remnant remnant) throws RemoteException;
}
