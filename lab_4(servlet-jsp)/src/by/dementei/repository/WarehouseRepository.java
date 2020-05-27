package by.dementei.repository;


import by.dementei.entity.Warehouse;

import java.sql.SQLException;
import java.util.List;

public interface WarehouseRepository {
    Warehouse get(int id) throws SQLException;

    List<Warehouse> getALL() throws SQLException;

    Warehouse insert(Warehouse warehouse) throws SQLException;

    Warehouse update(Warehouse warehouse) throws SQLException;

    void delete(int id) throws SQLException;
}
