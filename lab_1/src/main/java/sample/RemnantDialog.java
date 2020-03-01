package sample;

import entity.Product;
import entity.Remnant;
import entity.Warehouse;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class RemnantDialog extends Dialog<Remnant> {

    public RemnantDialog(Product product, Warehouse warehouse) {
        this.setTitle("Remnant");
        this.setHeaderText("Fill remnant information");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 50, 10, 10));

        TextField amount = new TextField();
        amount.setPromptText("amount");

        grid.add(new Label("Product:"), 0, 0);
        grid.add(new Label(product.toString()), 1, 0);
        grid.add(new Label("Warehouse:"), 0, 1);
        grid.add(new Label(warehouse.toString()), 1, 1);
        grid.add(new Label("Amount:"), 0, 2);
        grid.add(amount, 1, 2);

        Node addButton = this.getDialogPane().lookupButton(saveButtonType);
        addButton.setDisable(true);

        amount.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amount.setText(newValue.replaceAll("[^\\d]", ""));
            }
            addButton.setDisable(amount.getText().trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);
        amount.requestFocus();

        this.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Remnant(product.getId(), warehouse.getId(), Integer.parseInt(amount.getText()));
            }
            return null;
        });
    }

    public Remnant startDialog(){
        Optional<Remnant> result = this.showAndWait();
        return result.orElse(null);
    }
}
