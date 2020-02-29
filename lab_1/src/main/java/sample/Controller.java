package sample;

import entity.Product;
import entity.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private ListView<Product> listViewProducts;
    @FXML
    private ListView<Warehouse> listViewWarehouses;
    @FXML
    private MenuItem menuOpenData;

    private ObservableList<Product> productsObservableList;
    private ObservableList<Warehouse> warehousesObservableList;

    public Controller()  {
        productsObservableList = FXCollections.observableArrayList();
        warehousesObservableList = FXCollections.observableArrayList();
        productsObservableList.addAll(
                new Product(1, "pen", "beautiful"),
                new Product(2, "pencil", "red")
        );
        warehousesObservableList.addAll(
                new Warehouse(1, "Main", "Nezavisimosti 4"),
                new Warehouse(2, "Big", "Golubeva 10")
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listViewProducts.setItems(productsObservableList);
        listViewProducts.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DELETE){
                Product toDelete = listViewProducts.getSelectionModel().getSelectedItem();
                if(toDelete != null) {
                    System.out.println("DELETE: " + toDelete);
                }
            }
        });
        listViewProducts.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Product toDelete = listViewProducts.getSelectionModel().getSelectedItem();
                if(toDelete != null) {
                    System.out.println("OPEN: " + toDelete);
                }
            }
        });
        listViewWarehouses.setItems(warehousesObservableList);
        listViewWarehouses.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.DELETE){
                Warehouse toDelete = listViewWarehouses.getSelectionModel().getSelectedItem();
                if(toDelete != null) {
                    System.out.println("DELETE: " + toDelete);
                }
            }
        });
        listViewWarehouses.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Warehouse toDelete = listViewWarehouses.getSelectionModel().getSelectedItem();
                if(toDelete != null) {
                    System.out.println("OPEN: " + toDelete);
                }
            }
        });
    }


    public void addProduct(ActionEvent event){
        System.out.println("CREATE Product");
        AddProductDialog dialog = new AddProductDialog();
        Product product = dialog.startDialog();
        if(product != null) {
            System.out.println(product);
        }
    }

    public void addWarehouse(ActionEvent event){
        System.out.println("Create Warehouse");
        AddWarehouseDialog dialog = new AddWarehouseDialog();
        Warehouse warehouse = dialog.startDialog();
        if(warehouse != null) {
            System.out.println(warehouse);
        }
    }

    public void addRemnant(ActionEvent event){
        Product product = listViewProducts.getSelectionModel().getSelectedItem();
        Warehouse warehouse = listViewWarehouses.getSelectionModel().getSelectedItem();
        if (product != null && warehouse != null){
            System.out.println("Create Remnant" + product + " " + warehouse);
        }
    }


    public void addStudent(ActionEvent event){
//        AddStudentDialog dialog = new AddStudentDialog();
//        Student newStudent = dialog.startDialog();
//        addStudentToList(newStudent);
    }

//    private void addStudentToList(Student newStudent){
//        if(newStudent != null){
//            if(studentObservableList.stream().anyMatch(elem -> elem.getGroup() == newStudent.getGroup())){
//                long index = studentObservableList.stream()
//                        .takeWhile(elem -> elem.getGroup() != newStudent.getGroup())
//                        .count();
//                studentObservableList.add((int)index + 1, newStudent);
//            }else {
//                studentObservableList.add(new GroupInfo(newStudent.getGroup()));
//                studentObservableList.add(newStudent);
//            }
//            countAllGroupsMarks();
//        }
//    }



    public void readDataBase(ActionEvent event) {
//        try {
//            StudentDataBase db = new StudentDataBase();
//            ArrayList<Student> newStudents = db.readStudents();
//            studentObservableList.clear();
//            for(Student student: newStudents){
//                addStudentToList(student);
//            }
//            db.close();
//            Dialogs.showConfirmDialog("Success read from db");
//        } catch (ClassNotFoundException e) {
//            Dialogs.showErrorDialog("JDBC not found");
//        } catch (SQLException e) {
//            Dialogs.showErrorDialog("SQL error " + e.getMessage());
//        }
    }

    public void saveDataBase(ActionEvent event) {
//        try {
//            StudentDataBase db = new StudentDataBase();
//            db.writeStudents(new ArrayList<>(studentObservableList));
//            db.close();
//            Dialogs.showConfirmDialog("Success write to db");
//        } catch (ClassNotFoundException e) {
//            Dialogs.showErrorDialog("JDBC not found");
//        } catch (SQLException e) {
//            Dialogs.showErrorDialog("SQL error " + e.getMessage());
//        }
    }

    public void recreateDataBaseTable(ActionEvent event) {
//        try {
//            StudentDataBase db = new StudentDataBase();
//            db.createTable();
//            db.close();
//            Dialogs.showConfirmDialog("Success recreate table in db");
//        } catch (ClassNotFoundException e) {
//            Dialogs.showErrorDialog("JDBC not found");
//        } catch (SQLException e) {
//            Dialogs.showErrorDialog("SQL error " + e.getMessage());
//        }
    }
}
