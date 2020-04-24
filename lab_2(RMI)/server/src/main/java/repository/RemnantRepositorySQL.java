package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <b>Implementation of {@link SQLDatabase} for work with {@link Remnant remnants}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemnantRepositorySQL extends SQLDatabase implements RemnantRepository {
    /**
     * column "product_id" name in SQL table (reference to {@link Product product})
     */
    private final String PRODUCT_ID = "product_id";

    /**
     * column "warehouse_id" name in SQL table (reference to {@link Warehouse warehouse})
     */
    private final String WAREHOUSE_ID = "warehouse_id";

    /**
     * column "amount" name in SQL table
     */
    private final String AMOUNT = "amount";

    /**
     * constructor to create repository for specified table
     * @param tableName name of SQL table
     * @throws ClassNotFoundException if SQL driver not found
     * @throws RepositoryException if connection to database or table failed
     */
    public RemnantRepositorySQL(String tableName) throws ClassNotFoundException, RepositoryException {
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
                "(" + PRODUCT_ID + " INTEGER NOT NULL, " +
                WAREHOUSE_ID + " INTEGER NOT NULL, " +
                AMOUNT + " INTEGER , " +
                " UNIQUE (" + PRODUCT_ID + ", " + WAREHOUSE_ID + "), " +
                " foreign key (" + PRODUCT_ID + ") references products(id) on delete cascade, " +
                " foreign key (" + WAREHOUSE_ID + ") references warehouses (id) on delete cascade)";
        try {
            this.statement.executeUpdate(sql);
            System.out.println("Table '" + TABLE_NAME + "' connected successfully");
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * get sum amount of specified {@link Product product} on all warehouses
     * @param product {@link Product product} to search
     * @return sum amount
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public int getProductAmount(Product product) throws RepositoryException {
        String sql = "SELECT SUM (" + AMOUNT + ") AS answer FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + "=" + product.getId();
        System.out.println(sql);
        try (ResultSet rs = this.statement.executeQuery(sql)) {
            rs.next();
            return rs.getInt("answer");
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }

    }

    /**
     * get all {@link Remnant remnants} from SQL database that has reference to specified {@link Product product}
     * @param product {@link Product product} to search by
     * @return list of {@link Remnant remnants}
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public List<Remnant> getAllByProduct(Product product) throws RepositoryException {
        List<Remnant> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + " = " + product.getId();
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Remnant(product.getId(), resultSet.getInt(WAREHOUSE_ID), resultSet.getInt(AMOUNT)));

            }
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return list;
    }

    /**
     * get all {@link Remnant remnants} from SQL database that has reference to specified {@link Warehouse warehouse}
     * @param warehouse {@link Warehouse warehouse} to search by
     * @return list of {@link Remnant remnants}
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException {
        List<Remnant> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + WAREHOUSE_ID + " = " + warehouse.getId();
        System.out.println(sql);
        try (ResultSet resultSet = this.statement.executeQuery(sql)) {
            while (resultSet.next()) {
                list.add(new Remnant(resultSet.getInt(PRODUCT_ID), warehouse.getId(), resultSet.getInt(AMOUNT)));
            }
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return list;
    }

    /**
     * add new {@link Remnant remnant} to SQL database
     * @param remnant instance of {@link Remnant remnant} wishing to insert
     * @return instance of saved {@link Remnant remnant} from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public Remnant insert(Remnant remnant) throws RepositoryException {
        String sql = String.format(Locale.US, "INSERT INTO " + TABLE_NAME + " (" + PRODUCT_ID + ", " + WAREHOUSE_ID + " , " + AMOUNT +
                ") VALUES ('%d', '%d', '%d')", remnant.getProductId(), remnant.getWarehouseId(), remnant.getAmount());
        System.out.println(sql);
        try {
            this.statement.executeUpdate(sql);
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return remnant;
    }

    /**
     * update {@link Remnant remnant} in SQL database
     * @param remnant instance of {@link Remnant remnant} wishing to update
     * @return instance of updated {@link Remnant remnant} from SQL database
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public Remnant update(Remnant remnant) throws RepositoryException {
        String sql = String.format(Locale.US, "UPDATE " + TABLE_NAME + " SET " + AMOUNT + " = '%d' WHERE " + PRODUCT_ID + "=" + remnant.getProductId() +
                " AND " + WAREHOUSE_ID + "=" + remnant.getWarehouseId(), remnant.getAmount());
        System.out.println(sql);
        try {
            this.statement.executeUpdate(sql);
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return remnant;
    }

    /**
     * delete {@link Remnant remnant} from SQL database
     * @param remnant instance of {@link Remnant remnant} wishing to delete
     * @throws RepositoryException if {@link SQLException} happened
     */
    @Override
    public void delete(Remnant remnant) throws RepositoryException {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + PRODUCT_ID + "=" + remnant.getProductId() + " AND " + WAREHOUSE_ID + "=" + remnant.getWarehouseId();
        System.out.println(sql);
        try {
            this.statement.execute(sql);
        } catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
