package sample;

import entity.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.util.Pair;

import java.io.IOException;

public class RemnantListViewCell extends ListCell<Pair<Product, Integer>> {

    @FXML
    private Label productName;

    @FXML
    private Label amount;


    public RemnantListViewCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("remnant_info_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Pair<Product, Integer> remnant, boolean empty) {
        super.updateItem(remnant, empty);
        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            productName.setText(remnant.getKey().getName());
            amount.setText(String.valueOf(remnant.getValue()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

