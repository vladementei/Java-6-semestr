package service;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;
import repository.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * <b>Implementation of interface {@link RemoteService} of all server methods</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemoteServiceImplementation extends UnicastRemoteObject implements RemoteService {
    /**
     * instance interface of {@link ProductRepository product repository}
     */
    private ProductRepository productRepository;

    /**
     * instance interface of {@link WarehouseRepository warehouse repository}
     */
    private WarehouseRepository warehouseRepository;

    /**
     * instance interface of {@link RemnantRepository remnant repository}
     */
    private RemnantRepository remnantRepository;


    /**
     * constructor to set all repositories that are important for work of service
     * @param productRepository instance of {@link ProductRepository product repository}
     * @param warehouseRepository instance of {@link Warehouse warehouse repository}
     * @param remnantRepository instance of {@link RemnantRepository remnant repository}
     * @throws RemoteException if any error happened
     */
    public RemoteServiceImplementation(ProductRepository productRepository,
                                       WarehouseRepository warehouseRepository,
                                       RemnantRepository remnantRepository) throws RemoteException {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.remnantRepository = remnantRepository;
    }

    /**
     * get {@link Product product} by id from {@link ProductRepository product repository}
     * @param id unique identifier of {@link Product product}
     * @return {@link Product product} with id in param
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Product getProductById(int id) throws RemoteException {
        try {
            return this.productRepository.get(id);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * get all {@link Product products} from {@link ProductRepository product repository}
     * @return list of all {@link Product products}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public List<Product> getALLProducts() throws RemoteException {
        try {
            return this.productRepository.getALL();
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * add new {@link Product product} to {@link ProductRepository product repository}
     * @param product instance of {@link Product product} wishing to insert
     * @return instance of saved {@link Product product} in {@link ProductRepository product repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Product insertProduct(Product product) throws RemoteException {
        try {
            return this.productRepository.insert(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * update {@link Product product} in {@link ProductRepository product repository}
     * @param product instance of {@link Product product} wishing to update
     * @return instance of updated {@link Product product} in {@link ProductRepository product repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Product updateProduct(Product product) throws RemoteException {
        try {
            return this.productRepository.update(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * delete {@link Product product} from {@link ProductRepository product repository}
     * @param product instance of {@link Product product} wishing to delete
     * @throws RemoteException if error in repository happened
     */
    @Override
    public void deleteProduct(Product product) throws RemoteException {
        try {
            this.productRepository.delete(product.getId());
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * get {@link Warehouse warehouse} by id from {@link WarehouseRepository warehouse repository}
     * @param id unique identifier of {@link Warehouse warehouse}
     * @return {@link Warehouse warehouse} with id in param from {@link WarehouseRepository warehouse repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Warehouse getWarehouseById(int id) throws RemoteException {
        try {
            return this.warehouseRepository.get(id);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * get all {@link Warehouse warehouse} from {@link WarehouseRepository warehouse repository}
     * @return list of all {@link Warehouse warehouse} from {@link WarehouseRepository warehouse repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public List<Warehouse> getALLWarehouses() throws RemoteException {
        try {
            return this.warehouseRepository.getALL();
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * add new {@link Warehouse warehouse} to {@link WarehouseRepository warehouse repository}
     * @param warehouse instance of {@link Warehouse warehouse} wishing to insert
     * @return instance of saved {@link Warehouse warehouse} from {@link WarehouseRepository warehouse repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Warehouse insertWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            return this.warehouseRepository.insert(warehouse);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * update {@link Warehouse warehouse} in {@link WarehouseRepository warehouse repository}
     * @param warehouse instance of {@link Warehouse warehouse} wishing to update
     * @return instance of updated {@link Warehouse warehouse} from {@link WarehouseRepository warehouse repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            return this.warehouseRepository.update(warehouse);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * delete {@link Warehouse warehouse} from {@link WarehouseRepository warehouse repository}
     * @param warehouse instance of {@link Warehouse warehouse} wishing to delete
     * @throws RemoteException if error in repository happened
     */
    @Override
    public void deleteWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            this.warehouseRepository.delete(warehouse.getId());
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * calculate sum amount on all {@link Warehouse warehouses} of specified {@link Product product}
     * @param product {@link Product product} wishing to calculate sum
     * @return sum amount of all {@link Product products}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public int getProductAmount(Product product) throws RemoteException {
        try {
            return this.remnantRepository.getProductAmount(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * get all {@link Remnant remnants} from {@link RemnantRepository remnant repository} by specified {@link Product product}
     * @param product {@link Product product} for search
     * @return list of all {@link Remnant remnants} from {@link RemnantRepository remnant repository} with {@link Product product} in param
     * @throws RemoteException if error in repository happened
     */
    @Override
    public List<Remnant> getAllByProduct(Product product) throws RemoteException {
        try {
            return this.remnantRepository.getAllByProduct(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * get all {@link Remnant remnants} from {@link RemnantRepository remnant repository} by specified {@link Warehouse warehouse}
     * @param warehouse {@link Warehouse warehouse} for search
     * @return list of all {@link Remnant remnants} from {@link RemnantRepository remnant repository} with {@link Warehouse warehouse} in param
     * @throws RemoteException if error in repository happened
     */
    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            return this.remnantRepository.getAllByWarehouse(warehouse);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * add new {@link Remnant remnant} to {@link RemnantRepository remnant repository}
     * @param remnant instance of {@link Remnant remnant} wishing to insert
     * @return instance of saved {@link Remnant remnant} from {@link RemnantRepository remnant repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Remnant insert(Remnant remnant) throws RemoteException {
        try {
            return this.remnantRepository.insert(remnant);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * update {@link Remnant remnant} in {@link RemnantRepository remnant repository}
     * @param remnant instance of {@link Remnant remnant} wishing to update
     * @return instance of updated {@link Remnant remnant} from {@link RemnantRepository remnant repository}
     * @throws RemoteException if error in repository happened
     */
    @Override
    public Remnant update(Remnant remnant) throws RemoteException {
        try {
            return this.remnantRepository.update(remnant);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    /**
     * delete {@link Remnant remnant} from {@link RemnantRepository remnant repository}
     * @param remnant instance of {@link Remnant remnant} wishing to delete
     * @throws RemoteException if error in repository happened
     */
    @Override
    public void delete(Remnant remnant) throws RemoteException {
        try {
            this.remnantRepository.delete(remnant);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
