package repository;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import exception.RepositoryException;

import java.util.List;

/**
 * <b>Interface to define methods for working with {@link Remnant remnant} repositories</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public interface RemnantRepository {
    /**
     * get sum amount of specified product on all {@link Warehouse warehouses}
     * @param product instance of {@link Product product} to search
     * @return sum amount of specified {@link Product product}
     * @throws RepositoryException if any error in repository happened
     */
    int getProductAmount(Product product) throws RepositoryException;

    /**
     * get all {@link Remnant remnants} from database that have reference to specified {@link Product product}
     * @param product instance of {@link Product product} to search
     * @return list of all {@link Remnant remnants}
     * @throws RepositoryException if any error in repository happened
     */
    List<Remnant> getAllByProduct(Product product) throws RepositoryException;

    /**
     * get all {@link Remnant remnants} from database that have reference to specified {@link Warehouse warehouse}
     * @param warehouse instance of {@link Warehouse warehouse} to search
     * @return list of all {@link Remnant remnants}
     * @throws RepositoryException if any error in repository happened
     */
    List<Remnant> getAllByWarehouse(Warehouse warehouse) throws RepositoryException;

    /**
     * add new {@link Remnant remnant} to database
     * @param remnant instance of {@link Remnant remnant} wishing to insert
     * @return instance of saved {@link Remnant remnant} from database
     * @throws RepositoryException if any error in repository happened
     */
    Remnant insert(Remnant remnant) throws RepositoryException;

    /**
     * update {@link Remnant remnant} in database
     * @param remnant instance of {@link Remnant remnant} wishing to update
     * @return instance of updated {@link Remnant remnant} from database
     * @throws RepositoryException if any error in repository happened
     */
    Remnant update(Remnant remnant) throws RepositoryException;

    /**
     * delete {@link Remnant remnant} from database
     * @param remnant instance of {@link Remnant remnant} wishing to delete
     * @throws RepositoryException if if any error in repository happened
     */
    void delete(Remnant remnant) throws RepositoryException;
}
