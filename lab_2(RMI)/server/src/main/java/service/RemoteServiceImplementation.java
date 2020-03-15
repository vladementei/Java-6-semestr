package service;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;
import repository.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RemoteServiceImplementation extends UnicastRemoteObject implements RemoteService {
    private ProductRepository productRepository;
    private WarehouseRepository warehouseRepository;
    private RemnantRepository remnantRepository;


    public RemoteServiceImplementation(ProductRepository productRepository,
                                       WarehouseRepository warehouseRepository,
                                       RemnantRepository remnantRepository) throws RemoteException {
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.remnantRepository = remnantRepository;
    }

    @Override
    public Product getProductById(int id) throws RemoteException {
        try {
            return this.productRepository.get(id);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<Product> getALLProducts() throws RemoteException {
        try {
            return this.productRepository.getALL();
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Product insertProduct(Product product) throws RemoteException {
        try {
            return this.productRepository.insert(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Product updateProduct(Product product) throws RemoteException {
        try {
            return this.productRepository.update(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void deleteProduct(Product product) throws RemoteException {
        try {
            this.productRepository.delete(product.getId());
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Warehouse getWarehouseById(int id) throws RemoteException {
        try {
            return this.warehouseRepository.get(id);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<Warehouse> getALLWarehouses() throws RemoteException {
        try {
            return this.warehouseRepository.getALL();
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Warehouse insertWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            return this.warehouseRepository.insert(warehouse);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            return this.warehouseRepository.update(warehouse);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            this.warehouseRepository.delete(warehouse.getId());
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public int getProductAmount(Product product) throws RemoteException {
        try {
            return this.remnantRepository.getProductAmount(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<Remnant> getAllByProduct(Product product) throws RemoteException {
        try {
            return this.remnantRepository.getAllByProduct(product);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RemoteException {
        try {
            return this.remnantRepository.getAllByWarehouse(warehouse);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Remnant insert(Remnant remnant) throws RemoteException {
        try {
            return this.remnantRepository.insert(remnant);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public Remnant update(Remnant remnant) throws RemoteException {
        try {
            return this.remnantRepository.update(remnant);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    public void delete(Remnant remnant) throws RemoteException {
        try {
            this.remnantRepository.delete(remnant);
        } catch (RepositoryException e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
