package repository;

import entity.Product;
import exception.RepositoryException;

import java.util.List;

public interface ProductRepository {
    Product get(int id) throws RepositoryException;

    List<Product> getALL() throws RepositoryException;

    Product insert(Product product) throws RepositoryException;

    Product update(Product product) throws RepositoryException;

    void delete(int id) throws RepositoryException;
}
