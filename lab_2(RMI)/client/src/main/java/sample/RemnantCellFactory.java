package sample;

import entity.Product;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.Pair;

public class RemnantCellFactory implements Callback<ListView<Pair<Product, Integer>>, ListCell<Pair<Product, Integer>>> {

    @Override
    public ListCell<Pair<Product, Integer>> call(ListView<Pair<Product, Integer>> param) {
        return new RemnantListViewCell();
    }
}

