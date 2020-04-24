package repository;

import entity.Warehouse;
import exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <b>Implementation of {@link SQLDatabase} for work with {@link Warehouse warehouses}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class WarehouseRepositorySQL extends SQLDatabase implements WarehouseRepository {
    /**
     * column "id" name in SQL table
     */
    private final String ID = "id";

    /**
     * column "title" name in SQL table
     */
    private final String TITLE = "title";

    /**
     * column "location" name in SQL table
     */
    private final String LOCATION = "location";

    /**
     * constructor to create repository for specified table
     * @param tableName name of SQL table
     * @throws ClassNotFoundException if SQL driver not found
     * @throws RepositoryException if connection to database or table failed
     */
    public WarehouseRepositorySQL(String tableName) throws ClassNotFoundException, RepositoryException {
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
                TITLE + " VARCHAR(255), " +
                LOCATION + " VARCHAR(255), " +
                "PRIMARY KEY (" + ID + "))";
        try {
            this.statement.executeUpdate(sql);
            System.out.println("Table '" + TABLE_NAME + "' connected successfully");
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * get {@link Warehouse warehouse} from SQL database by id
     * @param id unique identifier of {@link Warehouse warehouse}
     * @return {@link Warehouse warehouse} with id in param from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public Warehouse get(int id) throws RepositoryException {
        Warehouse warehouse = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            if (resultSet.next()) {
                warehouse = new Warehouse(resultSet.getInt(ID), resultSet.getString(TITLE), resultSet.getString(LOCATION));
            }
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return warehouse;
    }

    /**
     * get all {@link Warehouse warehouses} from SQL database
     * @return list of all {@link Warehouse warehouses}
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public List<Warehouse> getALL() throws RepositoryException {
        List<Warehouse> products = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Warehouse(resultSet.getInt(ID), resultSet.getString(TITLE), resultSet.getString(LOCATION)));
            }
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return products;
    }

    /**
     * add new {@link Warehouse warehouse} to SQL database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to insert
     * @return instance of saved {@link Warehouse warehouse} from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public Warehouse insert(Warehouse warehouse) throws RepositoryException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (" + TITLE + ", " + LOCATION + ") VALUES ('%s', '%s') RETURNING " + ID,
                warehouse.getTitle(), warehouse.getLocation());
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            resultSet.next();
            warehouse.setId(resultSet.getInt(ID)); //warehouse = get(resultSet.getInt("id"))
            return warehouse;
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * update {@link Warehouse warehouse} in SQL database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to update
     * @return instance of updated {@link Warehouse warehouse} from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public Warehouse update(Warehouse warehouse) throws RepositoryException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET " + TITLE + " = '%s', " + LOCATION + " = '%s' " +
                "WHERE " + ID + "= '%d'", warehouse.getTitle(), warehouse.getLocation(), warehouse.getId());
        System.out.println(sql);
        try {
            this.statement.executeUpdate(sql);
            //warehouse = get(product.getId())
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return warehouse;
    }

    /**
     * delete {@link Warehouse warehouse} from SQL database
     * @param id id of {@link Warehouse warehouse} wishing to delete
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
