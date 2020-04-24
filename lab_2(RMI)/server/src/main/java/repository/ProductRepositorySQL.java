package repository;

import entity.Product;
import exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <b>Implementation of {@link SQLDatabase} for work with {@link Product products}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class ProductRepositorySQL extends SQLDatabase implements ProductRepository {
    /**
     * column "id" name in SQL table
     */
    private final String ID = "id";

    /**
     * column "name" name in SQL table
     */
    private final String NAME = "name";

    /**
     * column "description" name in SQL table
     */
    private final String DESCRIPTION = "description";

    /**
     * constructor to create repository for specified table
     * @param tableName name of SQL table
     * @throws ClassNotFoundException if SQL driver not found
     * @throws RepositoryException if connection to database or table failed
     */
    public ProductRepositorySQL(String tableName) throws ClassNotFoundException, RepositoryException {
        super(tableName);
    }

    /**
     * implementation of parent method to connect SQL table or create it if not exist
     * @throws RepositoryException if creation of table failed
     */
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
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * get {@link Product product} from SQL database by id
     * @param id unique identifier of {@link Product product}
     * @return {@link Product product} with id in param from SQL databse
     * @throws RepositoryException if {@link SQLException} happened
     */
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
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return product;
    }

    /**
     * get all {@link Product products} from SQL database
     * @return list of all {@link Product products}
     * @throws RepositoryException if {@link SQLException} happened
     */
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
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return products;
    }

    /**
     * add new {@link Product product} to SQL database
     * @param product instance of {@link Product product} wishing to insert
     * @return instance of saved {@link Product product} from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
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
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return product;
    }

    /**
     * update {@link Product product} in SQL database
     * @param product instance of {@link Product product} wishing to update
     * @return instance of updated {@link Product product} from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
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
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return product;
    }

    /**
     * delete {@link Product product} from SQL database
     * @param id id of {@link Product product} wishing to delete
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public void delete(int id) throws RepositoryException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        System.out.println(sql);
        try {
            this.statement.execute(sql);
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
