package by.dementei.repository;


import by.dementei.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    Product get(int id) throws SQLException;

    List<Product> getALL() throws SQLException;

    Product insert(Product product) throws SQLException;

    Product update(Product product) throws SQLException;

    void delete(int id) throws SQLException;
}
