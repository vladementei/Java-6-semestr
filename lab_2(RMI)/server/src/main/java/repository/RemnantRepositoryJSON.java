package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.Arrays;
import java.util.List;

/**
 * <b>Implementation of {@link JSONDatabase} for work with {@link Remnant remnants}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemnantRepositoryJSON extends JSONDatabase implements RemnantRepository {
    /**
     * attribute "product_id" name in JSON database (reference to {@link Product product})
     */
    private final String PRODUCT_ID = "productId";

    /**
     * attribute "warehouse_id" name in JSON database (reference to {@link Warehouse warehouse})
     */
    private final String WAREHOUSE_ID = "warehouseId";

    /**
     * attribute "amount" name in JSON database
     */
    private final String AMOUNT = "amount";


    /**
     * constructor to create repository for specified table
     * @param tableName name of JSON database
     * @throws RepositoryException if connection to database failed
     */
    public RemnantRepositoryJSON(String tableName) throws RepositoryException {
        super(tableName);
    }

    /**
     * get sum amount of specified {@link Product product} on all warehouses
     * @param product {@link Product product} to search
     * @return sum amount
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public int getProductAmount(Product product) throws RepositoryException {
        return getAllByProduct(product).stream().mapToInt(Remnant::getAmount).sum();
    }

    /**
     * get specified {@link Remnant remnant} from JSON database
     * @param remnant instance to search
     * @return result {@link Remnant remnant} from database
     * @throws RepositoryException if parser throws any error
     */
    private Remnant get(Remnant remnant) throws RepositoryException {
        System.out.println("get remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        try {
            Remnant[] found = document.read("$.remnant.[?(@." + PRODUCT_ID + " == " + remnant.getProductId() +
                    " && @." + WAREHOUSE_ID + " == " + remnant.getWarehouseId() + ")]", Remnant[].class);
            if (found.length == 0) {
                return null;
            } else {
                return found[0];
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }

    }

    /**
     * get all {@link Remnant remnants} from JSON database that has reference to specified {@link Product product}
     * @param product {@link Product product} to search by
     * @return list of {@link Remnant remnants}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Remnant> getAllByProduct(Product product) throws RepositoryException {
        System.out.println("get all remnants with product id = " + product.getId());
        try {
            return Arrays.asList(document.read("$.remnant.[?(@." + PRODUCT_ID + " == " + product.getId() + ")]", Remnant[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * get all {@link Remnant remnants} from JSON database that has reference to specified {@link Warehouse warehouse}
     * @param warehouse {@link Warehouse warehouse} to search by
     * @return list of {@link Remnant remnants}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException {
        System.out.println("get all remnants with warehouse id = " + warehouse.getId());
        try {
            return Arrays.asList(document.read("$.remnant.[?(@." + WAREHOUSE_ID + " == " + warehouse.getId() + ")]", Remnant[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * add new {@link Remnant remnant} to JSON database
     * @param remnant instance of {@link Remnant remnant} wishing to insert
     * @return instance of saved {@link Remnant remnant} from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Remnant insert(Remnant remnant) throws RepositoryException {
        System.out.println("insert remnant  " + remnant);
        try {
            Remnant foundDB = get(remnant);
            if (foundDB == null) {
                document.add(".remnant", remnant);
                saveToDataBase();
            } else {
                throw new RepositoryException("Such Remnant already exist");
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
        return remnant;
    }

    /**
     * update {@link Remnant remnant} in JSON database
     * @param remnant instance of {@link Remnant remnant} wishing to update
     * @return instance of updated {@link Remnant remnant} from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Remnant update(Remnant remnant) throws RepositoryException {
        System.out.println("update remnant " + remnant);
        try {
            Remnant fromDB = get(remnant);
            if (fromDB != null) {
                delete(fromDB);
                document.add(".remnant", remnant);
                saveToDataBase();
            } else {
                throw new RepositoryException("No remnant found with such id's");
            }
            return remnant;
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }


    /**
     * delete {@link Remnant remnant} from JSON database
     * @param remnant instance of {@link Remnant remnant} wishing to delete
     * @throws RepositoryException if  parser throws any error
     */
    @Override
    public void delete(Remnant remnant) throws RepositoryException {
        System.out.println("delete remnant with product id = " + remnant.getProductId() + " , warehouse id = " + remnant.getWarehouseId());
        try {
            document.delete("$.remnant.[?(@." + PRODUCT_ID + " == " + remnant.getProductId() +
                    " && @." + WAREHOUSE_ID + " == " + remnant.getWarehouseId() + ")]");
            saveToDataBase();
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}


