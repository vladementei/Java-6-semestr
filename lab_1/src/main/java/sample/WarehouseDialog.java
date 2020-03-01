package sample;

import entity.Product;
import entity.Warehouse;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.util.Pair;

import java.util.Optional;

public class WarehouseDialog extends Dialog<Warehouse> {
    public WarehouseDialog(Warehouse warehouse, ObservableList<Pair<Product, Integer>> remnants) {
        this.setTitle("Warehouse");
        this.setHeaderText("Fill warehouse information");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Title");
        title.setText(warehouse != null ? warehouse.getTitle() : "");
        TextField location = new TextField();
        location.setPromptText("Location");
        location.setText(warehouse != null ? warehouse.getLocation() : "");


        grid.add(new Label("Title:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Location:"), 0, 1);
        grid.add(location, 1, 1);
        if (remnants != null){
            grid.add(new Label("Product name"), 0,2);
            Label amount = new Label("Amount");
            GridPane.setHalignment(amount, HPos.RIGHT);
            grid.add(amount, 1, 2);

            ListView<Pair<Product, Integer>> listView = new ListView<>();
            listView.setCellFactory(new RemnantCellFactory());
            listView.setItems(remnants);
            grid.add(listView, 0, 3, 2, 1);
        }



        Node addButton = this.getDialogPane().lookupButton(saveButtonType);
        addButton.setDisable(warehouse == null);

        title.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || location.getText().trim().isEmpty());
        });

        location.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || title.getText().trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);
        title.requestFocus();

        this.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Warehouse(warehouse != null ? warehouse.getId() : 0, title.getText(), location.getText());
            }
            return null;
        });
    }

    public Warehouse startDialog(){
        Optional<Warehouse> result = this.showAndWait();
        return result.orElse(null);
    }
}
