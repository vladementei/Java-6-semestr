package service;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * <b>Interface with declaration of all server methods</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public interface RemoteService extends Remote {
    /**
     * get {@link Product product} by id
     * @param id unique identifier of {@link Product product}
     * @return {@link Product product} with id in param
     * @throws RemoteException if server error happened
     */
    Product getProductById(int id) throws RemoteException;

    /**
     * get all {@link Product products} on server
     * @return list of all {@link Product products}
     * @throws RemoteException if server error happened
     */
    List<Product> getALLProducts() throws RemoteException;

    /**
     * add new {@link Product product} to server database
     * @param product instance of {@link Product product} wishing to insert
     * @return instance of saved {@link Product product} to database
     * @throws RemoteException if server error happened
     */
    Product insertProduct(Product product) throws RemoteException;

    /**
     * update {@link Product product} in server database
     * @param product instance of {@link Product product} wishing to update
     * @return instance of updated {@link Product product} in database
     * @throws RemoteException if server error happened
     */
    Product updateProduct(Product product) throws RemoteException;

    /**
     * delete {@link Product product} from server database
     * @param product instance of {@link Product product} wishing to delete
     * @throws RemoteException if server error happened
     */
    void deleteProduct(Product product) throws RemoteException;

    /**
     * get {@link Warehouse warehouse} by id
     * @param id unique identifier of {@link Warehouse warehouse}
     * @return {@link Warehouse warehouse} with id in param
     * @throws RemoteException if server error happened
     */
    Warehouse getWarehouseById(int id) throws RemoteException;

    /**
     * get all {@link Warehouse warehouse} on server
     * @return list of all {@link Warehouse warehouse}
     * @throws RemoteException if server error happened
     */
    List<Warehouse> getALLWarehouses() throws RemoteException;

    /**
     * add new {@link Warehouse warehouse} to server database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to insert
     * @return instance of saved {@link Warehouse warehouse} to database
     * @throws RemoteException if server error happened
     */
    Warehouse insertWarehouse(Warehouse warehouse) throws RemoteException;

    /**
     * update {@link Warehouse warehouse} in server database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to update
     * @return instance of updated {@link Warehouse warehouse} in database
     * @throws RemoteException if server error happened
     */
    Warehouse updateWarehouse(Warehouse warehouse) throws RemoteException;

    /**
     * delete {@link Warehouse warehouse} from server database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to delete
     * @throws RemoteException if server error happened
     */
    void deleteWarehouse(Warehouse warehouse) throws RemoteException;

    /**
     * calculate sum amount on all {@link Warehouse warehouses} of specified {@link Product product}
     * @param product {@link Product product} wishing to calculate sum
     * @return sum amount of all {@link Product products}
     * @throws RemoteException if server error happened
     */
    int getProductAmount(Product product) throws RemoteException;

    /**
     * get all {@link Remnant remnants} on server by specified {@link Product product}
     * @param product {@link Product product} for search
     * @return list of all {@link Remnant remnants} with {@link Product product} in param
     * @throws RemoteException if server error happened
     */
    List<Remnant> getAllByProduct(Product product) throws RemoteException;

    /**
     * get all {@link Remnant remnants} on server by specified {@link Warehouse warehouse}
     * @param warehouse {@link Warehouse warehouse} for search
     * @return list of all {@link Remnant remnants} with {@link Warehouse warehouse} in param
     * @throws RemoteException if server error happened
     */
    List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RemoteException;

    /**
     * add new {@link Remnant remnant} to server database
     * @param remnant instance of {@link Remnant remnant} wishing to insert
     * @return instance of saved {@link Remnant remnant} to database
     * @throws RemoteException if server error happened
     */
    Remnant insert(Remnant remnant) throws RemoteException;

    /**
     * update {@link Remnant remnant} in server database
     * @param remnant instance of {@link Remnant remnant} wishing to update
     * @return instance of updated {@link Remnant remnant} in database
     * @throws RemoteException if server error happened
     */
    Remnant update(Remnant remnant) throws RemoteException;

    /**
     * delete {@link Remnant remnant} from server database
     * @param remnant instance of {@link Remnant remnant} wishing to delete
     * @throws RemoteException if server error happened
     */
    void delete(Remnant remnant) throws RemoteException;
}
