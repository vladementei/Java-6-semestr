package sample;

import entity.Product;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;


public class AddProductDialog extends Dialog<Product> {

    public AddProductDialog() {
        this.setTitle("Add product");
        this.setHeaderText("Fill product information");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 50, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Name");
        TextField description = new TextField();
        description.setPromptText("Description");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(description, 1, 1);

        Node addButton = this.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || description.getText().trim().isEmpty());
        });

        description.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || name.getText().trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);
        name.requestFocus();

        this.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Product(0, name.getText(), description.getText());
            }
            return null;
        });
    }

    public Product startDialog(){
        Optional<Product> result = this.showAndWait();
        return result.orElse(null);
    }
}
