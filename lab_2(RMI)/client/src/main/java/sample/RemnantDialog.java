package sample;

import entity.Remnant;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import service.RemoteServiceController;

import java.rmi.RemoteException;
import java.util.Optional;

/**
 * <b>UI dialog to create or update {@link Remnant remnant}</b>
 * @author <h2><i style="color: green;">Uladzislau Dzemiantsei</i></h2>
 * @version <span style="color: blue;">1.0</span>
 */
public class RemnantDialog extends Dialog<Remnant> {

    /**
     * constructor to initialize dialog
     * @param remnant if null, means it is creation dialog, otherwise - update dialog where param is {@link Remnant remnant} to update
     */
    public RemnantDialog(Remnant remnant) {
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
        amount.setText(String.valueOf(remnant.getAmount()));

        String productLabel = String.valueOf(remnant.getProductId());
        String warehouseLabel = String.valueOf(remnant.getWarehouseId());
        try {
            productLabel = RemoteServiceController.getService().getProductById(remnant.getProductId()).toString();
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }
        try {
            warehouseLabel = RemoteServiceController.getService().getWarehouseById(remnant.getWarehouseId()).toString();
        } catch (RemoteException e) {
            e.printStackTrace();
            Dialogs.showErrorDialog(e.getMessage());
        }

        grid.add(new Label("Product:"), 0, 0);
        grid.add(new Label(productLabel), 1, 0);
        grid.add(new Label("Warehouse:"), 0, 1);
        grid.add(new Label(warehouseLabel), 1, 1);
        grid.add(new Label("Amount:"), 0, 2);
        grid.add(amount, 1, 2);

        Node addButton = this.getDialogPane().lookupButton(saveButtonType);

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
                return new Remnant(remnant.getProductId(), remnant.getWarehouseId(), Integer.parseInt(amount.getText()));
            }
            return null;
        });
    }

    /**
     * Method that runs dialog
     * @return created or updated {@link Remnant remnant} after close
     */
    public Remnant startDialog(){
        Optional<Remnant> result = this.showAndWait();
        return result.orElse(null);
    }
}
