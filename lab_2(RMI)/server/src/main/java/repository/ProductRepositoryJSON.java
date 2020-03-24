package repository;

import entity.Product;
import entity.Remnant;
import exception.RepositoryException;

import java.util.*;

public class ProductRepositoryJSON extends JSONDatabase implements ProductRepository {
    private final String ID = "id";
    private final String NAME = "name";
    private final String DESCRIPTION = "description";
    private final RemnantRepository remnantRepository;

    public ProductRepositoryJSON(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName);
        this.remnantRepository = remnantRepository;
    }

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

    @Override
    public List<Product> getALL() throws RepositoryException {
        System.out.println("get all products");
        try {
            return Arrays.asList(document.read("$.product.*", Product[].class));
        } catch (Throwable e) {
            throw new RepositoryException(e.getMessage(), e.fillInStackTrace());
        }
    }

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
