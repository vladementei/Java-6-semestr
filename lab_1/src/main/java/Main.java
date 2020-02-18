import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import repository.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        ProductRepository productRepository = new ProductRepositorySQL("products");
        Product product = new Product(1, "pencil", "grey and long");
        System.out.println(productRepository.save(product) + "\n");
        System.out.println(productRepository.get(1) + "\n");
        System.out.println(productRepository.save(product) + "\n");
        System.out.println(productRepository.save(product) + "\n");
        product.setName("pen");
        System.out.println(productRepository.update(product) + "\n");
        productRepository.delete(2);
        productRepository.getALL().forEach(System.out::println);

        WarehouseRepository warehouseRepository = new WarehouseRepositorySQL("warehouses");
        Warehouse warehouse = new Warehouse(1, "Main warehouse", "Nezavisimosti 4");
        System.out.println(warehouseRepository.save(warehouse) + "\n");
        System.out.println(warehouseRepository.get(1) + "\n");
        System.out.println(warehouseRepository.save(warehouse) + "\n");
        System.out.println(warehouseRepository.save(warehouse) + "\n");
        warehouse.setTitle("Warehouse # 1");
        System.out.println(warehouseRepository.update(warehouse) + "\n");
        warehouseRepository.delete(2);
        warehouseRepository.getALL().forEach(System.out::println);

        RemnantRepository remnantRepository = new RemnantRepositorySQL("product_warehouse");
        Remnant remnant = new Remnant(1, 1, 5);
        System.out.println(remnantRepository.save(remnant) + "\n");
        remnant.setWarehouseId(3);
        System.out.println(remnantRepository.save(remnant) + "\n");
        remnant.setAmount(10);
        System.out.println(remnantRepository.update(remnant) + "\n");
        System.out.println(remnantRepository.getAllByWarehouse(warehouse));
        System.out.println(remnantRepository.getAllByProduct(product));
        System.out.println(remnantRepository.getProductAmount(product));
    }
}
