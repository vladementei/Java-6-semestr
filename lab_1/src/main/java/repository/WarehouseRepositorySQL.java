package repository;

import entity.Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WarehouseRepositorySQL extends SQLDatabase implements WarehouseRepository {

    public WarehouseRepositorySQL() throws ClassNotFoundException, SQLException {
        super("warehouses");
    }

    @Override
    public void connectTable() throws SQLException {
        System.out.println("Connecting table " + TABLE_NAME);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(id SERIAL, " +
                " title VARCHAR(255), " +
                " location VARCHAR(255), " +
                "PRIMARY KEY (id))";
        this.statement.executeUpdate(sql);
        System.out.println("Table '" + TABLE_NAME + "' connected successfully");
    }

    @Override
    public Warehouse get(int id) throws SQLException {
        Warehouse warehouse = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)){
            if(resultSet.next()) {
                warehouse = new Warehouse(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("location"));
            }
        }
        return warehouse;
    }

    @Override
    public List<Warehouse> getALL() throws SQLException {
        List<Warehouse> products = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)){
            while (resultSet.next()) {
                products.add(new Warehouse(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("location")));
            }
        }
        return products;    }

    @Override
    public Warehouse save(Warehouse warehouse) throws SQLException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (title, location) VALUES ('%s', '%s') RETURNING id",
                warehouse.getTitle(),
                warehouse.getLocation());
        System.out.println(sql);
        ResultSet resultSet = this.statement.executeQuery(sql);
        resultSet.next();
        warehouse.setId(resultSet.getInt("id")); //warehouse = get(resultSet.getInt("id"))
        return warehouse;
    }

    @Override
    public Warehouse update(Warehouse warehouse) throws SQLException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET title = '%s', location = '%s' WHERE id=" + warehouse.getId(),
                warehouse.getTitle(), warehouse.getLocation());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        //warehouse = get(product.getId())
        return warehouse;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = " + id;
        System.out.println(sql);
        this.statement.execute(sql);
    }
}
