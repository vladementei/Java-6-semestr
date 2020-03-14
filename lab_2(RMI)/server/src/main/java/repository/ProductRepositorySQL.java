package repository;

import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductRepositorySQL extends SQLDatabase implements ProductRepository {
    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";

    public ProductRepositorySQL(String tableName) throws ClassNotFoundException, SQLException {
        super(tableName);
    }

    @Override
    public void connectTable() throws SQLException {
        System.out.println("Connecting table " + TABLE_NAME);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + ID + " SERIAL, " +
                NAME + " VARCHAR(255), " +
                DESCRIPTION + " VARCHAR(255), " +
                "PRIMARY KEY (" + ID + "))";
        this.statement.executeUpdate(sql);
        System.out.println("Table '" + TABLE_NAME + "' connected successfully");
    }

    @Override
    public Product get(int id) throws SQLException {
        Product product = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=" + id;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            if (resultSet.next()) {
                product = new Product(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(DESCRIPTION));
            }
        }
        return product;
    }

    @Override
    public List<Product> getALL() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(DESCRIPTION)));
            }
        }
        return products;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (" + NAME + ", " + DESCRIPTION +
                        ") VALUES ('%s', '%s') RETURNING id",
                product.getName(),
                product.getDescription());
        System.out.println(sql);
        ResultSet resultSet = this.statement.executeQuery(sql);
        resultSet.next();
        product.setId(resultSet.getInt(ID)); //product = get(resultSet.getInt("id"))
        return product;
    }

    @Override
    public Product update(Product product) throws SQLException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET " + NAME + " = '%s', " + DESCRIPTION +
                        " = '%s' WHERE " + ID + " = '%d'",
                product.getName(), product.getDescription(), product.getId());
        System.out.println(sql);
        this.statement.executeUpdate(sql);
        //product = get(product.getId())
        return product;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        System.out.println(sql);
        this.statement.execute(sql);
    }
}
