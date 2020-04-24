package repository;

import entity.Product;
import entity.Remnant;
import exception.RepositoryException;

import java.util.*;

/**
 * <b>Implementation of {@link JSONDatabase} for work with {@link Product products}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class ProductRepositoryJSON extends JSONDatabase implements ProductRepository {
    /**
     * attribute "id" name in JSON database
     */
    private final String ID = "id";

    /**
     * attribute "name" name in JSON database
     */
    private final String NAME = "name";

    /**
     * attribute "description" name in JSON database
     */
    private final String DESCRIPTION = "description";

    /**
     * instance of {@link RemnantRepository remnant repository} to implement cascade delete
     */
    private final RemnantRepository remnantRepository;

    /**
     * constructor to create repository for specified table
     * @param tableName name of JSON database
     * @param remnantRepository instance of {@link RemnantRepository remnant repository}
     * @throws RepositoryException if connection to database  failed
     */
    public ProductRepositoryJSON(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName);
        this.remnantRepository = remnantRepository;
    }

    /**
     * get {@link Product product} from JSON database by id
     * @param id unique identifier of {@link Product product}
     * @return {@link Product product} with id in param from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Product get(int id) throws RepositoryException {
        System.out.println("get product with id = " + id);
        try {
            Product[] products = document.read("$.product.[?(@." + ID + " == " + id + ")]", Product[].class);
            if (products.length != 0) {
                return products[0];
            } else {
                return null;
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * get all {@link Product products} from JSON database
     * @return list of all {@link Product products}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Product> getALL() throws RepositoryException {
        System.out.println("get all products");
        try {
            return Arrays.asList(document.read("$.product.*", Product[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * add new {@link Product product} to JSON database
     * @param product instance of {@link Product product} wishing to insert
     * @return instance of saved {@link Product product} from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Product insert(Product product) throws RepositoryException {
        System.out.println("insert product  " + product);
        try {
            product.setId(Collections.max(Arrays.asList(document.read("$..id", Integer[].class))) + 1);
        } catch (Throwable e) {
            product.setId(1);
        }
        try {
            document.add(".product", product);
            saveToDataBase();
            return product;
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * update {@link Product product} in JSON database
     * @param product instance of {@link Product product} wishing to update
     * @return instance of updated {@link Product product} from JSON database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Product update(Product product) throws RepositoryException {
        System.out.println("update product " + product);
        try {
            Product fromDB = get(product.getId());
            if (fromDB != null) {
                //delete(fromDB.getId());
                document.delete("$.product.[?(@." + ID + " == " + fromDB.getId() + ")]");
                document.add(".product", product);
                saveToDataBase();
            } else {
                throw new RepositoryException("No product with id == " + product.getId());
            }
            return product;
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

    /**
     * delete {@link Product product} from JSON database
     * @param id id of {@link Product product} wishing to delete
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public void delete(int id) throws RepositoryException {
        System.out.println("delete product with id = " + id);
        try {
            document.delete("$.product.[?(@." + ID + " == " + id + ")]");
            saveToDataBase();
            for (Remnant remnant : remnantRepository.getAllByProduct(new Product(id, "", ""))) {
                remnantRepository.delete(remnant);
            }
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }
}
