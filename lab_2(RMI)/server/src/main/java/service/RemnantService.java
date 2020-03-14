package service;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import repository.RemnantRepository;
import repository.RemnantRepositorySQL;

import java.sql.SQLException;
import java.util.List;

public class RemnantService {
    private static RemnantService remnantService;
    private static RemnantRepository remnantRepository;

    static {
        try {
            remnantRepository = new RemnantRepositorySQL("product_warehouse");
            remnantService = new RemnantService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private RemnantService() {
    }

    public static RemnantService getRemnantService() {
        return remnantService;
    }

    public int getProductAmount(Product product) throws SQLException {
        return remnantRepository.getProductAmount(product);
    }

    public List<Remnant> getAllByProduct(Product product) throws SQLException {
        return remnantRepository.getAllByProduct(product);
    }

    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws SQLException {
        return remnantRepository.getAllByWarehouse(warehouse);
    }

    public Remnant insert(Remnant remnant) throws SQLException {
        return remnantRepository.insert(remnant);
    }

    public Remnant update(Remnant remnant) throws SQLException {
        return remnantRepository.update(remnant);
    }

    public void delete(Remnant remnant) throws SQLException {
        remnantRepository.delete(remnant);
    }
}
