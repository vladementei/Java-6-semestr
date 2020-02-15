package repository;

import entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    Product get(int id) throws SQLException;

    List<Product> getALL() throws SQLException;

    Product save(Product product) throws SQLException;

    Product update(Product product) throws SQLException;

    void delete(int id) throws SQLException;
}
