package sample;

import entity.Warehouse;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class AddWarehouseDialog extends Dialog<Warehouse> {
    public AddWarehouseDialog() {
        this.setTitle("Add warehouse");
        this.setHeaderText("Fill warehouse information");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 50, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Title");
        TextField location = new TextField();
        location.setPromptText("Location");

        grid.add(new Label("Title:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Location:"), 0, 1);
        grid.add(location, 1, 1);

        Node addButton = this.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        title.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || location.getText().trim().isEmpty());
        });

        location.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || title.getText().trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);
        title.requestFocus();

        this.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Warehouse(0, title.getText(), location.getText());
            }
            return null;
        });
    }

    public Warehouse startDialog(){
        Optional<Warehouse> result = this.showAndWait();
        return result.orElse(null);
    }
}
