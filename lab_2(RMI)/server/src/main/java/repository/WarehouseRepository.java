package repository;

import entity.Warehouse;
import exception.RepositoryException;

import java.util.List;

public interface WarehouseRepository {
    Warehouse get(int id) throws RepositoryException;

    List<Warehouse> getALL() throws RepositoryException;

    Warehouse insert(Warehouse warehouse) throws RepositoryException;

    Warehouse update(Warehouse warehouse) throws RepositoryException;

    void delete(int id) throws RepositoryException;
}
