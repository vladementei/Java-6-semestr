package by.dementei.repository;


import by.dementei.entity.Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WarehouseRepositorySQL extends SQLDatabase implements WarehouseRepository {

    private final String ID = "id";
    private final String TITLE = "title";
    private final String LOCATION = "location";

    public WarehouseRepositorySQL(String tableName) throws ClassNotFoundException, SQLException {
        super(tableName);
    }

    @Override
    public void connectTable() throws SQLException {
        System.out.println("Connecting table " + TABLE_NAME);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + ID + " SERIAL, " +
                TITLE + " VARCHAR(255), " +
                LOCATION + " VARCHAR(255), " +
                "PRIMARY KEY (" + ID + "))";
        this.statement.executeUpdate(sql);
        System.out.println("Table '" + TABLE_NAME + "' connected successfully");
    }

    @Override
    public Warehouse get(int id) throws SQLException {
        Warehouse warehouse = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            if (resultSet.next()) {
                warehouse = new Warehouse(resultSet.getInt(ID), resultSet.getString(TITLE), resultSet.getString(LOCATION));
            }
        }
        return warehouse;
    }

    @Override
    public List<Warehouse> getALL() throws SQLException {
        List<Warehouse> products = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Warehouse(resultSet.getInt(ID), resultSet.getString(TITLE), resultSet.getString(LOCATION)));
            }
        }
        return products;
    }

    @Override
    public Warehouse insert(Warehouse warehouse) throws SQLException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (" + TITLE + ", " + LOCATION + ") VALUES ('%s', '%s') RETURNING " + ID,
                warehouse.getTitle(), warehouse.getLocation());
        System.out.println(sql);
        ResultSet resultSet = this.statement.executeQuery(sql);
        resultSet.next();
        warehouse.setId(resultSet.getInt(ID)); //warehouse = get(resultSet.getInt("id"))
        return warehouse;
    }

    @Override
    public Warehouse update(Warehouse warehouse) throws SQLException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET " + TITLE + " = '%s', " + LOCATION + " = '%s' " +
                "WHERE " + ID + "= '%d'", warehouse.getTitle(), warehouse.getLocation(), warehouse.getId());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        //warehouse = get(product.getId())
        return warehouse;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        System.out.println(sql);
        this.statement.execute(sql);
    }
}
