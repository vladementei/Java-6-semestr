package sample;

import entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

/**
 * <b>Class to define custom {@link Product product} cell</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class ProductListViewCell extends ListCell<Product> {

    /**
     * {@link Product product} id
     */
    @FXML
    private Label id;

    /**
     * {@link Product product} name
     */
    @FXML
    private Label name;

    /**
     * {@link Product product} description
     */
    @FXML
    private Label description;

    /**
     * Default constructor
     */
    public ProductListViewCell() {
        loadFXML();
    }

    /**
     * Set view of {@link Product product} cell
     */
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

    /**
     * Binds view with {@link Product product}variable
     * @param product current {@link Product product} cell model
     * @param empty if current {@link Product product} cell is empty
     */
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
