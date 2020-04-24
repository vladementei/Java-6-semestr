package repository;

import entity.Product;
import entity.ProductContainer;
import entity.Remnant;
import exception.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <b>Implementation of {@link XMLDatabase} for work with {@link Product products}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class ProductRepositoryXML extends XMLDatabase<ProductContainer> implements ProductRepository {
    /**
     * instance of {@link RemnantRepository remnant repository} to implement cascade delete
     */
    private final RemnantRepository remnantRepository;

    /**
     * constructor to create repository for specified table
     * @param tableName name of XML database
     * @param remnantRepository instance of {@link RemnantRepository remnant repository}
     * @throws RepositoryException if connection to database  failed
     */
    public ProductRepositoryXML(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName, new ProductContainer());
        this.remnantRepository = remnantRepository;
    }

    /**
     * get {@link Product product} from XML database by id
     * @param id unique identifier of {@link Product product}
     * @return {@link Product product} with id in param from XML database
     * @throws RepositoryException if parser throws any error happened
     */
    @Override
    public Product get(int id) throws RepositoryException {
        System.out.println("get product with id = " + id);
        List<Product> products = entity.getProducts().stream().filter(product -> product.getId() == id).collect(Collectors.toList());
        return products.size() != 0 ? products.get(0) : null;
    }

    /**
     * get all {@link Product products} from XML database
     * @return list of all {@link Product products}
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public List<Product> getALL() throws RepositoryException {
        System.out.println("get all products");
        return entity.getProducts();
    }

    /**
     * add new {@link Product product} to XML database
     * @param product instance of {@link Product product} wishing to insert
     * @return instance of saved {@link Product product} from XML database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Product insert(Product product) throws RepositoryException {
        System.out.println("insert product  " + product);
        int maxId = entity.getProducts().stream().map(Product::getId).max(Integer::compareTo).orElse(0);
        product.setId(maxId + 1);
        entity.getProducts().add(product);
        saveToDataBase();
        return product;
    }

    /**
     * update {@link Product product} in XML database
     * @param product instance of {@link Product product} wishing to update
     * @return instance of updated {@link Product product} from XML database
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public Product update(Product product) throws RepositoryException {
        System.out.println("update product " + product);
        Product fromDB = get(product.getId());
        if (fromDB != null) {
            entity.getProducts().remove(fromDB);
            entity.getProducts().add(product);
            saveToDataBase();
        } else {
            throw new RepositoryException("No product with id == " + product.getId());
        }
        return product;
    }

    /**
     * delete {@link Product product} from XML database
     * @param id id of {@link Product product} wishing to delete
     * @throws RepositoryException if parser throws any error
     */
    @Override
    public void delete(int id) throws RepositoryException {
        System.out.println("delete product with id = " + id);
        Product fromDB = get(id);
        if (fromDB != null){
            entity.getProducts().remove(fromDB);
            saveToDataBase();
        }
        for (Remnant remnant : remnantRepository.getAllByProduct(new Product(id, "", ""))) {
            remnantRepository.delete(remnant);
        }
    }
}
