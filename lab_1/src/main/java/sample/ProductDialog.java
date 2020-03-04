package sample;

import entity.Product;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import service.RemnantService;

import java.sql.SQLException;
import java.util.Optional;


public class ProductDialog extends Dialog<Product> {

    private RemnantService remnantService = RemnantService.getRemnantService();

    public ProductDialog(Product product) {
        this.setTitle("Product");
        this.setHeaderText("Fill product information");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 50, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Name");
        name.setText(product != null ? product.getName() : "");
        TextField description = new TextField();
        description.setPromptText("Description");
        description.setText(product != null ? product.getDescription() : "");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(description, 1, 1);

        if(product != null) {
            try {
                grid.add(new Label("Total amount: "), 0 , 2);
                grid.add(new Label(String.valueOf(remnantService.getProductAmount(product))), 1, 2);
            } catch (SQLException e) {
                e.printStackTrace();
                Dialogs.showErrorDialog(e.getMessage());
            }
        }

        Node addButton = this.getDialogPane().lookupButton(saveButtonType);
        addButton.setDisable(product == null);

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || description.getText().trim().isEmpty());
        });

        description.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty() || name.getText().trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);
        name.requestFocus();

        this.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Product(product != null ? product.getId() : 0, name.getText(), description.getText());
            }
            return null;
        });
    }

    public Product startDialog(){
        Optional<Product> result = this.showAndWait();
        return result.orElse(null);
    }
}
