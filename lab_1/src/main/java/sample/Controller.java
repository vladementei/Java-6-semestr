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
        listViewProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewProducts.setOnKeyPressed(event -> {
//            if(event.getCode() == KeyCode.DELETE){
//                List<GroupInfo> toDelete  = listView.getSelectionModel().getSelectedItems().stream().sorted((o1, o2) -> {
//                    if(o2 instanceof Student){
//                            return 1;
//                    }
//                    return -1;
//                }).collect(Collectors.toList());
//
//                for(GroupInfo elem: toDelete){
//                    if(elem instanceof Student){
//                        studentObservableList.remove(elem);
//                    }else {
//                       studentObservableList.setAll(studentObservableList.stream()
//                                                    .filter(s -> s.getGroup() != elem.getGroup())
//                                                    .collect(Collectors.toList()));
//                    }
//                }
//                countAllGroupsMarks();
//            }
        });
        listViewWarehouses.setItems(warehousesObservableList);
        listViewWarehouses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewWarehouses.setOnKeyPressed(event -> {});
    }



    public void openFile(ActionEvent event){

    }

    public void saveFile(ActionEvent event){

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

    public void analyze(ActionEvent event){
//        List<Map.Entry<Integer, List<GroupInfo>>> list = studentObservableList.stream()
//                .collect(Collectors.groupingBy(GroupInfo::getGroup))
//                .entrySet()
//                .stream()
//                .sorted((o1,o2)->Double.compare(o2.getValue().get(0).getAverageMark(), o1.getValue().get(0).getAverageMark()))
//                .collect(Collectors.toList());
//        studentObservableList.clear();
//        list.forEach((map)-> studentObservableList.addAll(map.getValue()));
    }


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
