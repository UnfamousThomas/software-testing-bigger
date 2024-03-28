package ee.ut.math.tvt.salessystem.ui.controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import ee.ut.math.tvt.salessystem.SalesSystemException;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import ee.ut.math.tvt.salessystem.logic.InvalidNumberException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    private final SalesSystemDAO dao;
    private static final Logger log = LogManager.getLogger(StockController.class);

    @FXML
    private Button addItem;
    @FXML
    private TableView<StockItem> warehouseTableView;
    @FXML
    private TextField barCodeField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;

    public StockController(SalesSystemDAO dao) {
        this.dao = dao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshStockItems();
        // TODO refresh view after adding new items
        log.info("StockController started");
        barCodeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    barCodeField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        quantityField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantityField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*.\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    public void refreshButtonClicked() {
        refreshStockItems();
    }

    private void refreshStockItems() {
        if(this.warehouseTableView != null) { //Just an if so tests pass bc javafx is weird sometimes
            warehouseTableView.setItems(FXCollections.observableList(dao.findStockItems()));
            warehouseTableView.refresh();
        }
    }

    public void error(String title, String text) {
        if(this.warehouseTableView == null) return;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(text);
        alert.showAndWait();
    }
    private StockItem getStockItemByBarcode(Long code) {
        try {
            return dao.findStockItem(code);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    @FXML
    public void addProductButtonClicked() {
        long barCode = Long.parseLong(barCodeField.getText());
        String name = nameField.getText();
        String desc = "";
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        addProduct(barCode, name, desc, price, quantity);
        resetProductField();
        refreshStockItems();
    }
    public void addProduct(Long barCode, String name, String desc, double price, int quantity){
        if(price < 0) {
            error("Invalid price", "Please choose an price above 0");
            throw new InvalidNumberException(price);
        }
        if(name.length() > 50) {
            error("Invalid name", "Please choose a product name below 50 characters in length");
            throw new InvalidNumberException(quantity);
        }
        StockItem item = getStockItemByBarcode(barCode);
        if (item == null) {
            item = new StockItem(barCode, name, desc, price, quantity);
            log.debug("New product created");
        } else {
            quantity += item.getQuantity();
            item.setQuantity(quantity);
            item.setPrice(price);
            if(!name.equalsIgnoreCase(item.getName())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Different name");
                alert.setContentText("An item with that id was found and its quantity was increased by " + quantity + " but its name is different.");
                alert.showAndWait();
            }
        }
        dao.beginTransaction();
        dao.saveStockItem(item);
        dao.commitTransaction();
        refreshStockItems();

    }
    public void resetProductField() {
        barCodeField.setText("");
        quantityField.setText("");
        nameField.setText("");
        priceField.setText("");
    }

}
