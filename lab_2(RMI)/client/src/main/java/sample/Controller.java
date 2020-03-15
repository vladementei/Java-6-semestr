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
import service.RemoteServiceController;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private ListView<Product> listViewProducts;
    @FXML
    private ListView<Warehouse> listViewWarehouses;

    private ObservableList<Product> productsObservableList;
    private ObservableList<Warehouse> warehousesObservableList;

    public Controller()  {
        productsObservableList = FXCollections.observableArrayList();
        warehousesObservableList = FXCollections.observableArrayList();
        try {
            RemoteServiceController.setRemoteService("rmi://localhost:8080/sql-server");
            try {
                productsObservableList.addAll(RemoteServiceController.getService().getALLProducts());
            }catch (RemoteException e) {
                e.printStackTrace();
                Dialogs.showErrorDialog(e.getMessage());
            } finally {
                warehousesObservableList.addAll(RemoteServiceController.getService().getALLWarehouses());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
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
                        RemoteServiceController.getService().deleteProduct(toDelete);
                        productsObservableList.remove(toDelete);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Dialogs.showErrorDialog(e.getMessage());
                    }
                }
            }
        });
        listViewProducts.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Product product = listViewProducts.getSelectionModel().getSelectedItem();
                if(product != null) {
                    ProductDialog dialog = new ProductDialog(product);
                    Product updated = dialog.startDialog();
                    if (updated != null && !product.equals(updated)){
                        try {
                            updated = RemoteServiceController.getService().updateProduct(updated);
                            productsObservableList.remove(product);
                            productsObservableList.add(updated);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            Dialogs.showErrorDialog(e.getMessage());
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
                        RemoteServiceController.getService().deleteWarehouse(toDelete);
                        warehousesObservableList.remove(toDelete);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Dialogs.showErrorDialog(e.getMessage());
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
                        remnants.addAll(RemoteServiceController.getService().getAllByWarehouse(warehouse).stream()
                         .map(remnant -> {
                            try {
                                return new Pair<>(RemoteServiceController.getService().getProductById(remnant.getProductId()), remnant.getAmount());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                                Dialogs.showErrorDialog(e.getMessage());
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));

                        WarehouseDialog dialog = new WarehouseDialog(warehouse, remnants);
                        Warehouse updated = dialog.startDialog();
                        if(updated != null && !warehouse.equals(updated)){
                            updated = RemoteServiceController.getService().updateWarehouse(updated);
                            warehousesObservableList.remove(warehouse);
                            warehousesObservableList.add(updated);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Dialogs.showErrorDialog(e.getMessage());
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
                product = RemoteServiceController.getService().insertProduct(product);
                productsObservableList.add(product);
            } catch (RemoteException e) {
                e.printStackTrace();
                Dialogs.showErrorDialog(e.getMessage());
            }
        }
    }

    public void addWarehouse(ActionEvent event){
        WarehouseDialog dialog = new WarehouseDialog(null, null);
        Warehouse warehouse = dialog.startDialog();
        if(warehouse != null) {
            try {
                warehouse = RemoteServiceController.getService().insertWarehouse(warehouse);
                warehousesObservableList.add(warehouse);
            } catch (RemoteException e) {
                e.printStackTrace();
                Dialogs.showErrorDialog(e.getMessage());
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
                    RemoteServiceController.getService().insert(remnant);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Dialogs.showErrorDialog(e.getMessage());
                }
            }
        } else {
            Dialogs.showErrorDialog("First select one product and one warehouse!!!");
        }
    }

}
