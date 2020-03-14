package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;

import java.sql.SQLException;
import java.util.List;

public interface RemnantRepository {
    int getProductAmount(Product product) throws SQLException;

    List<Remnant> getAllByProduct(Product product) throws SQLException;

    List<Remnant> getAllByWarehouse(Warehouse warehouse) throws SQLException;

    Remnant insert(Remnant remnant) throws SQLException;

    Remnant update(Remnant remnant) throws SQLException;

    void delete(Remnant remnant) throws SQLException;
}
