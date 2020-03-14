package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.List;

public interface RemnantRepository {
    int getProductAmount(Product product) throws RepositoryException;

    List<Remnant> getAllByProduct(Product product) throws RepositoryException;

    List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException;

    Remnant insert(Remnant remnant) throws RepositoryException;

    Remnant update(Remnant remnant) throws RepositoryException;

    void delete(Remnant remnant) throws RepositoryException;
}
