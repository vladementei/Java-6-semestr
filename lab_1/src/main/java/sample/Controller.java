package sample;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.util.Pair;
import service.ProductService;
import service.RemnantService;
import service.WarehouseService;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private ListView<Product> listViewProducts;
    @FXML
    private ListView<Warehouse> listViewWarehouses;

    private ObservableList<Product> productsObservableList;
    private ObservableList<Warehouse> warehousesObservableList;

    private ProductService productService = ProductService.getProductService();
    private WarehouseService warehouseService = WarehouseService.getWarehouseService();
    private RemnantService remnantService = RemnantService.getRemnantService();


    public Controller()  {
        productsObservableList = FXCollections.observableArrayList();
        warehousesObservableList = FXCollections.observableArrayList();
        try {
            productsObservableList.addAll(productService.getALLProducts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            warehousesObservableList.addAll(warehouseService.getALLWarehouses());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewProducts.setItems(productsObservableList);
        listViewProducts.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DELETE){
                Product toDelete = listViewProducts.getSelectionModel().getSelectedItem();
                if(toDelete != null) {
                    try {
                        productService.deleteProduct(toDelete);
                        productsObservableList.remove(toDelete);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        listViewProducts.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Product product = listViewProducts.getSelectionModel().getSelectedItem();
                if(product != null) {
                    System.out.println("OPEN: " + product);
                    ProductDialog dialog = new ProductDialog(product);
                    Product updated = dialog.startDialog();
                    if (updated != null && !product.equals(updated)){
                        try {
                            updated = productService.updateProduct(updated);
                            productsObservableList.remove(product);
                            productsObservableList.add(updated);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        listViewWarehouses.setItems(warehousesObservableList);
        listViewWarehouses.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DELETE){
                Warehouse toDelete = listViewWarehouses.getSelectionModel().getSelectedItem();
                if(toDelete != null) {
                    try {
                        warehouseService.deleteWarehouse(toDelete);
                        warehousesObservableList.remove(toDelete);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        listViewWarehouses.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Warehouse warehouse = listViewWarehouses.getSelectionModel().getSelectedItem();
                if(warehouse != null) {
                    try {
                        ObservableList<Pair<Product, Integer>> remnants = FXCollections.observableArrayList();
                        remnants.addAll(remnantService.getAllByWarehouse(warehouse).stream()
                         .map(remnant -> {
                            try {
                                return new Pair<>(productService.getProductById(remnant.getProductId()), remnant.getAmount());
                            } catch (SQLException e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));

                        WarehouseDialog dialog = new WarehouseDialog(warehouse, remnants);
                        Warehouse updated = dialog.startDialog();
                        if(updated != null && !warehouse.equals(updated)){
                            updated = warehouseService.updateWarehouse(updated);
                            warehousesObservableList.remove(warehouse);
                            warehousesObservableList.add(updated);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }


    public void addProduct(ActionEvent event){
        ProductDialog dialog = new ProductDialog(null);
        Product product = dialog.startDialog();
        if(product != null) {
            try {
                product = productService.insertProduct(product);
                productsObservableList.add(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void addWarehouse(ActionEvent event){
        WarehouseDialog dialog = new WarehouseDialog(null, null);
        Warehouse warehouse = dialog.startDialog();
        if(warehouse != null) {
            try {
                warehouse = warehouseService.insertWarehouse(warehouse);
                warehousesObservableList.add(warehouse);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRemnant(ActionEvent event){
        Product product = listViewProducts.getSelectionModel().getSelectedItem();
        Warehouse warehouse = listViewWarehouses.getSelectionModel().getSelectedItem();
        if (product != null && warehouse != null){
            RemnantDialog dialog = new RemnantDialog(new Remnant(product.getId(), warehouse.getId(), 0));
            Remnant remnant = dialog.startDialog();
            if (remnant != null) {
                try {
                    remnantService.insert(remnant);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
