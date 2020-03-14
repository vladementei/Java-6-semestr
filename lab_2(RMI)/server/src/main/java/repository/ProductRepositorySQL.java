package repository;

import entity.Product;
import exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductRepositorySQL extends SQLDatabase implements ProductRepository {
    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";

    public ProductRepositorySQL(String tableName) throws ClassNotFoundException, RepositoryException {
        super(tableName);
    }

    @Override
    public void connectTable() throws RepositoryException {
        System.out.println("Connecting table " + TABLE_NAME);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(" + ID + " SERIAL, " +
                NAME + " VARCHAR(255), " +
                DESCRIPTION + " VARCHAR(255), " +
                "PRIMARY KEY (" + ID + "))";
        try {
            this.statement.executeUpdate(sql);
            System.out.println("Table '" + TABLE_NAME + "' connected successfully");
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Product get(int id) throws RepositoryException {
        Product product = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=" + id;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            if (resultSet.next()) {
                product = new Product(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(DESCRIPTION));
            }
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
        return product;
    }

    @Override
    public List<Product> getALL() throws RepositoryException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(DESCRIPTION)));
            }
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
        return products;
    }

    @Override
    public Product insert(Product product) throws RepositoryException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (" + NAME + ", " + DESCRIPTION +
                        ") VALUES ('%s', '%s') RETURNING id",
                product.getName(),
                product.getDescription());
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            resultSet.next();
            product.setId(resultSet.getInt(ID)); //product = get(resultSet.getInt("id"))
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
        return product;
    }

    @Override
    public Product update(Product product) throws RepositoryException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET " + NAME + " = '%s', " + DESCRIPTION +
                        " = '%s' WHERE " + ID + " = '%d'",
                product.getName(), product.getDescription(), product.getId());
        System.out.println(sql);
        try {
            this.statement.executeUpdate(sql);
            //product = get(product.getId())
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
        return product;
    }

    @Override
    public void delete(int id) throws RepositoryException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        System.out.println(sql);
        try {
            this.statement.execute(sql);
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.getCause());
        }
    }
}
