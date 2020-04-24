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
import service.EndPointsConfiguration;
import service.RemoteServiceController;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <b>Controller part of MVC hierarchy of UI</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">3.0</span>
 */
public class Controller implements Initializable {

    /**
     * View of all {@link Product products}
     */
    @FXML
    private ListView<Product> listViewProducts;
    /**
     * View of all {@link Warehouse warehouses }
     */
    @FXML
    private ListView<Warehouse> listViewWarehouses;

    /**
     * Model for view of all {@link Product products}
     */
    private ObservableList<Product> productsObservableList;

    /**
     * Model for view of all {@link Warehouse warehouses}
     */
    private ObservableList<Warehouse> warehousesObservableList;

    /**
     * Method is used to clear all previous views of lists and initialize new by calling getAllEntities
     * @throws RemoteException if getAll from server throws exception
     */
    private void readAllDB() throws RemoteException{
        try {
            productsObservableList.clear();
            productsObservableList.addAll(RemoteServiceController.getService().getALLProducts());
        }catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        } finally {
            warehousesObservableList.clear();
            warehousesObservableList.addAll(RemoteServiceController.getService().getALLWarehouses());
        }
    }

    /**
     * Constructor of controller, create models and read default DB - SQL
     */
    public Controller()  {
        productsObservableList = FXCollections.observableArrayList();
        warehousesObservableList = FXCollections.observableArrayList();
        try {
            RemoteServiceController.setRemoteService(EndPointsConfiguration.getInstance().SQL_ENDPOINT);
            readAllDB();
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }

    /**
     * initialize is used to set mouse double click and key delete listeners.
     * @param location default param, not used
     * @param resources default param, not used
     */
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


    /**
     * Method call {@link ProductDialog Product dialog} to create new {@link Product product}
     * @param event contains info about click event
     */
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

    /**
     * Method call {@link WarehouseDialog Warehouse dialog} to create new {@link Warehouse warehouse}
     * @param event contains info about click event
     */
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

    /**
     * Method call {@link RemnantDialog Remnant dialog} to create new {@link Remnant remnant}
     * <br> one {@link Product product} and one {@link Warehouse warehouse} must be selected first
     * @param event contains info about click event
     */
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

    /**
     * Switches UI to SQL database
     * @param event contains info about click event
     */
    public void connectSQLEndPoint(ActionEvent event) {
        try {
            RemoteServiceController.setRemoteService(EndPointsConfiguration.getInstance().SQL_ENDPOINT);
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }

    /**
     * Switches UI to JSON database
     * @param event contains info about click event
     */
    public void connectJSONEndPoint(ActionEvent event) {
        try {
            RemoteServiceController.setRemoteService(EndPointsConfiguration.getInstance().JSON_ENDPOINT);
            readAllDB();
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }

    /**
     * Switches UI to XML database
     * @param event contains info about click event
     */
    public void connectXMLEndPoint(ActionEvent event) {
        try {
            RemoteServiceController.setRemoteService(EndPointsConfiguration.getInstance().XML_ENDPOINT);
            readAllDB();
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
    }
}
