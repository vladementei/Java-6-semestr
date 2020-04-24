package repository;

import entity.Product;
import exception.RepositoryException;

import java.util.List;

/**
 * <b>Interface to define methods for working with {@link Product product} repositories</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public interface ProductRepository {
    /**
     * get {@link Product product} from database by id
     * @param id unique identifier of {@link Product product}
     * @return {@link Product product} with id in param from database
     * @throws RepositoryException if any error in repository happened
     */
    Product get(int id) throws RepositoryException;

    /**
     * get all {@link Product products} from database
     * @return list of all {@link Product products}
     * @throws RepositoryException if any error in repository happened
     */
    List<Product> getALL() throws RepositoryException;

    /**
     * add new {@link Product product} to database
     * @param product instance of {@link Product product} wishing to insert
     * @return instance of saved {@link Product product} from database
     * @throws RepositoryException if any error in repository happened
     */
    Product insert(Product product) throws RepositoryException;

    /**
     * update {@link Product product} in database
     * @param product instance of {@link Product product} wishing to update
     * @return instance of updated {@link Product product} from database
     * @throws RepositoryException if any error in repository happened
     */
    Product update(Product product) throws RepositoryException;

    /**
     * delete {@link Product product} from database
     * @param id id of {@link Product product} wishing to delete
     * @throws RepositoryException if if any error in repository happened
     */
    void delete(int id) throws RepositoryException;
}
