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

    private final String PRODUCT_ID = "product_id";
    private final String WAREHOUSE_ID = "warehouse_id";
    private final String AMOUNT = "amount";

    public RemnantRepositorySQL(String tableName) throws ClassNotFoundException, SQLException {
        super(tableName);
    }

    @Override
    public void connectTable() throws SQLException {
        System.out.println("Connecting table " + TABLE_NAME);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + PRODUCT_ID + " INTEGER NOT NULL, " +
                WAREHOUSE_ID + " INTEGER NOT NULL, " +
                AMOUNT + " INTEGER , " +
                " UNIQUE (" + PRODUCT_ID + ", " + WAREHOUSE_ID + "), " +
                " foreign key (" + PRODUCT_ID + ") references products(id) on delete cascade, " +
                " foreign key (" + WAREHOUSE_ID + ") references warehouses (id) on delete cascade)";
        this.statement.executeUpdate(sql);
        System.out.println("Table '" + TABLE_NAME + "' connected successfully");
    }

    @Override
    public int getProductAmount(Product product) throws SQLException {
        String sql = "SELECT SUM (" + AMOUNT + ") AS answer FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + "=" + product.getId();
        System.out.println(sql);
        ResultSet rs = this.statement.executeQuery(sql);
        rs.next();
        return rs.getInt("answer");
    }

    @Override
    public List<Remnant> getAllByProduct(Product product) throws SQLException {
        List<Remnant> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + " = " + product.getId();
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Remnant(product.getId(), resultSet.getInt(WAREHOUSE_ID), resultSet.getInt(AMOUNT)));

            }
        }
        return list;
    }

    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws SQLException {
        List<Remnant> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + WAREHOUSE_ID + " = " + warehouse.getId();
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Remnant(resultSet.getInt(PRODUCT_ID), warehouse.getId(), resultSet.getInt(AMOUNT)));
            }
        }
        return list;
    }

    @Override
    public Remnant insert(Remnant remnant) throws SQLException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (" + PRODUCT_ID + ", " + WAREHOUSE_ID + " , " + AMOUNT +
                ") VALUES ('%d', '%d', '%d')", remnant.getProductId(), remnant.getWarehouseId(), remnant.getAmount());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        return remnant;
    }

    @Override
    public Remnant update(Remnant remnant) throws SQLException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET " + AMOUNT + " = '%d' WHERE " + PRODUCT_ID + "=" + remnant.getProductId() +
                " AND " + WAREHOUSE_ID + "=" + remnant.getWarehouseId(), remnant.getAmount());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        return remnant;
    }

    @Override
    public void delete(Remnant remnant) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + "=" + remnant.getProductId() + " AND " + WAREHOUSE_ID + "=" + remnant.getWarehouseId();
        System.out.println(sql);
        this.statement.execute(sql);
    }
}
