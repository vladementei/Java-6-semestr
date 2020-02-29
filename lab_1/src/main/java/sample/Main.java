//import entity.Product;
//import entity.Remnant;
//import entity.Warehouse;
//import repository.*;
//
//import java.sql.SQLException;
//
//public class Main {
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ProductRepository productRepository = new ProductRepositorySQL("products");
//        Product product = new Product(1, "pencil", "grey and long");
//        System.out.println(productRepository.insert(product) + "\n");
//        System.out.println(productRepository.get(1) + "\n");
//        System.out.println(productRepository.insert(product) + "\n");
//        System.out.println(productRepository.insert(product) + "\n");
//        product.setName("pen");
//        System.out.println(productRepository.update(product) + "\n");
//        productRepository.delete(2);
//        productRepository.getALL().forEach(System.out::println);
//        System.out.println();
//
//        WarehouseRepository warehouseRepository = new WarehouseRepositorySQL("warehouses");
//        Warehouse warehouse = new Warehouse(1, "Main warehouse", "Nezavisimosti 4");
//        System.out.println(warehouseRepository.insert(warehouse) + "\n");
//        System.out.println(warehouseRepository.get(1) + "\n");
//        System.out.println(warehouseRepository.insert(warehouse) + "\n");
//        System.out.println(warehouseRepository.insert(warehouse) + "\n");
//        warehouse.setTitle("Warehouse # 1");
//        System.out.println(warehouseRepository.update(warehouse) + "\n");
//        warehouseRepository.delete(2);
//        warehouseRepository.getALL().forEach(System.out::println);
//        System.out.println();
//
//
//        product = new Product(1, "pencil", "grey and long");
//        warehouse = new Warehouse(1, "Main warehouse", "Nezavisimosti 4");
//        RemnantRepository remnantRepository = new RemnantRepositorySQL("product_warehouse");
//        Remnant remnant = new Remnant(1, 1, 5);
//        System.out.println(remnantRepository.insert(remnant) + "\n");
//        remnant.setWarehouseId(3);
//        System.out.println(remnantRepository.insert(remnant) + "\n");
//        remnant.setAmount(10);
//        System.out.println(remnantRepository.update(remnant) + "\n");
//        System.out.println(remnantRepository.getAllByWarehouse(warehouse) + "\n");
//        System.out.println(remnantRepository.getAllByProduct(product) + "\n");
//        System.out.println(remnantRepository.getProductAmount(product) + "\n");
//    }
//}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Products and warehouses");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
