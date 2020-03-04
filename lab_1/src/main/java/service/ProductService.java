package service;

import entity.Product;
import repository.ProductRepository;
import repository.ProductRepositorySQL;
import sample.Dialogs;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private static ProductService productService;
    private static ProductRepository productRepository;

    static {
        try {
            productRepository = new ProductRepositorySQL("products");
            productService = new ProductService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }

    private ProductService() {
    }

    public static ProductService getProductService() {
        return productService;
    }

    public Product getProductById(int id) throws SQLException {
        return productRepository.get(id);
    }

    public List<Product> getALLProducts() throws SQLException {
        return productRepository.getALL();
    }

    public Product insertProduct(Product product) throws SQLException {
        return productRepository.insert(product);
    }

    public Product updateProduct(Product product) throws SQLException {
        return productRepository.update(product);
    }

    public void deleteProduct(Product product) throws SQLException {
        productRepository.delete(product.getId());
    }
}
