package repository;

import entity.Warehouse;
import exception.RepositoryException;

import java.util.List;

/**
 * <b>Interface to define methods for working with {@link Warehouse warehouse} repositories</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public interface WarehouseRepository {
    /**
     * get {@link Warehouse warehouse} from database by id
     * @param id unique identifier of {@link Warehouse warehouse}
     * @return {@link Warehouse warehouse} with id in param from database
     * @throws RepositoryException if any error in repository happened
     */
    Warehouse get(int id) throws RepositoryException;

    /**
     * get all {@link Warehouse warehouses} from database
     * @return list of all {@link Warehouse warehouses}
     * @throws RepositoryException if any error in repository happened
     */
    List<Warehouse> getALL() throws RepositoryException;

    /**
     * add new {@link Warehouse warehouse} to database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to insert
     * @return instance of saved {@link Warehouse warehouse} from database
     * @throws RepositoryException if any error in repository happened
     */
    Warehouse insert(Warehouse warehouse) throws RepositoryException;

    /**
     * update {@link Warehouse warehouse} in database
     * @param warehouse instance of {@link Warehouse warehouse} wishing to update
     * @return instance of updated {@link Warehouse warehouse} from database
     * @throws RepositoryException if any error in repository happened
     */
    Warehouse update(Warehouse warehouse) throws RepositoryException;

    /**
     * delete {@link Warehouse warehouse} from database
     * @param id id of {@link Warehouse warehouse} wishing to delete
     * @throws RepositoryException if if any error in repository happened
     */
    void delete(int id) throws RepositoryException;
}
