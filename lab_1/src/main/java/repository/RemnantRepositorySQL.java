package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RemnantRepositorySQL extends SQLDatabase implements RemnantRepository {

    public RemnantRepositorySQL() throws ClassNotFoundException, SQLException {
        super("product_warehouse");
    }

    @Override
    public void connectTable() throws SQLException {
        System.out.println("Connecting table " + TABLE_NAME);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(product_id INTEGER NOT NULL , " +
                " warehouse_id INTEGER NOT NULL, " +
                " amount INTEGER , " +
                " foreign key (product_id) references products(id), " +
                " foreign key (warehouse_id) references warehouses (id))";
        this.statement.executeUpdate(sql);
        System.out.println("Table '" + TABLE_NAME + "' connected successfully");
    }

    @Override
    public List<Remnant> getAllByProduct(Product product) throws SQLException {
        List<Remnant> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE product_id = " + product.getId();
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Remnant(product.getId(),
                        resultSet.getInt("warehouse_id"),
                        resultSet.getInt("amount")));

            }
        }
        return list;
    }

    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws SQLException {
        List<Remnant> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE warehouse_id = " + warehouse.getId();
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Remnant(resultSet.getInt("product_id"),
                        warehouse.getId(),
                        resultSet.getInt("amount")));

            }
        }
        return list;
    }

    @Override
    public Remnant save(Remnant remnant) throws SQLException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (product_id, warehouse_id, amount) VALUES ('%d', '%d', '%d')",
                remnant.getProductId(),
                remnant.getWarehouseId(),
                remnant.getAmount());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        return remnant;
    }

    @Override
    public Remnant update(Remnant remnant) throws SQLException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET amount = '%d' WHERE product_id=" + remnant.getProductId() +
                        " AND warehouse_id=" + remnant.getWarehouseId(),
                remnant.getAmount());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        //product = get(product.getId())
        return remnant;
    }

    @Override
    public void delete(Remnant remnant) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE product_id=" + remnant.getProductId() + " AND warehouse_id=" + remnant.getWarehouseId();
        System.out.println(sql);
        this.statement.execute(sql);
    }
}
