package ee.ut.math.tvt.salessystem.ui.controllers;

import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.Purchase;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryController implements Initializable {

    private final SalesSystemDAO dao;

    private static final Logger log = LogManager.getLogger(HistoryController.class);

    @FXML
    private Button refreshHistoryButton;

    @FXML
    private Button showLastTenPurchasesButton;

    @FXML
    private Button showPurchasesBetweenDates;

    @FXML
    private DatePicker firstDatePicker;

    @FXML
    private DatePicker secondDatePicker;

    @FXML
    private TableView<Purchase> historyTableView;

    @FXML
    private TableView<SoldItem> purchaseTableView;

    private Purchase lastClickedPurchase;
    private LocalDateTime firstDateTime;
    private LocalDateTime secondDateTime;

    public HistoryController(SalesSystemDAO dao) {
        this.dao = dao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshHistory();
        historyTableView.setOnMouseClicked(event -> {
            lastClickedPurchase = historyTableView.getSelectionModel().getSelectedItem();
            refreshSelectedPurchase();
        });
        refreshHistory();
        log.info("History Controller started.");
    }

    @FXML
    public void refreshButtonClicked() {
        refreshHistory();
    }

    @FXML
    public void showLastTenPurchasesClicked() {
        historyTableView.setItems(FXCollections.observableList(dao.getLastTenPurchases()));
        historyTableView.refresh();
    }

    @FXML
    public void getFirstDate() {
       LocalDate date = firstDatePicker.getValue();
       firstDateTime = date.atStartOfDay();
    }

    @FXML
    public void getSecondDate() {
        LocalDate date = secondDatePicker.getValue();
        secondDateTime = date.atTime(23, 59, 59, 999999999);
    }

    @FXML
    public void showPurchasesBetweenDatesClicked() {
        if (firstDateTime != null && secondDateTime != null) {
            if (firstDateTime.isAfter(secondDateTime)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please enter smaller date first");
                alert.showAndWait();
                return;
            }
            List<Purchase> result = dao.getPurchasesBetweenDates(firstDateTime, secondDateTime);
            if (result != null) {
                historyTableView.setItems(FXCollections.observableList(result));
                historyTableView.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("No purchases to show");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please enter two dates");
            alert.showAndWait();
        }
    }

    private void refreshHistory() {
        historyTableView.setItems(FXCollections.observableList(dao.getPurchaseList()));
        historyTableView.refresh();
    }

    private void refreshSelectedPurchase() {
        purchaseTableView.setItems(FXCollections.observableList(lastClickedPurchase.getPurchasedItems()));
        purchaseTableView.refresh();
    }
}
