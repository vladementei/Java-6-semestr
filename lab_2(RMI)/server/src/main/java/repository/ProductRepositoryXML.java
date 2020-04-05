package repository;

import entity.Product;
import entity.ProductContainer;
import entity.Remnant;
import exception.RepositoryException;

import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryXML extends XMLDatabase<ProductContainer> implements ProductRepository {
    private final RemnantRepository remnantRepository;


    public ProductRepositoryXML(String tableName, RemnantRepository remnantRepository) throws RepositoryException {
        super(tableName, new ProductContainer());
        this.remnantRepository = remnantRepository;
    }

    @Override
    public Product get(int id) throws RepositoryException {
        System.out.println("get product with id = " + id);
        List<Product> products = entity.getProducts().stream().filter(product -> product.getId() == id).collect(Collectors.toList());
        return products.size() != 0 ? products.get(0) : null;
    }

    @Override
    public List<Product> getALL() throws RepositoryException {
        System.out.println("get all products");
        return entity.getProducts();
    }

    @Override
    public Product insert(Product product) throws RepositoryException {
        System.out.println("insert product  " + product);
        int maxId = entity.getProducts().stream().map(Product::getId).max(Integer::compareTo).orElse(0);
        product.setId(maxId + 1);
        entity.getProducts().add(product);
        saveToDataBase();
        return product;
    }

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
