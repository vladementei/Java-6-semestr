package by.dementei.service;

import by.dementei.entity.Warehouse;
import by.dementei.repository.WarehouseRepository;
import by.dementei.repository.WarehouseRepositorySQL;

import java.sql.SQLException;
import java.util.List;

public class WarehouseService {
    private static WarehouseService warehouseService;
    private static WarehouseRepository warehouseRepository;

    static {
        try {
            warehouseRepository = new WarehouseRepositorySQL("warehouses");
            warehouseService = new WarehouseService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            //Dialogs.showErrorDialog(e.getMessage());
        }
    }

    private WarehouseService() {
    }

    public static WarehouseService getWarehouseService() {
        return warehouseService;
    }

    public Warehouse getWarehouseById(int id) throws SQLException {
        return warehouseRepository.get(id);
    }

    public List<Warehouse> getALLWarehouses() throws SQLException {
        return warehouseRepository.getALL();
    }

    public Warehouse insertWarehouse(Warehouse warehouse) throws SQLException {
        return warehouseRepository.insert(warehouse);
    }

    public Warehouse updateWarehouse(Warehouse warehouse) throws SQLException {
        return warehouseRepository.update(warehouse);
    }

    public void deleteWarehouse(Warehouse warehouse) throws SQLException {
        warehouseRepository.delete(warehouse.getId());
    }
}
