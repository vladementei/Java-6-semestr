package sample;

import entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ProductListViewCell extends ListCell<Product> {

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label description;

    public ProductListViewCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("product_info_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            id.setText(String.valueOf(product.getId()));
            name.setText(product.getName());
            description.setText(product.getDescription());
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
