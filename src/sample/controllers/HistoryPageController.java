package sample.controllers;

import com.opencsv.CSVWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.models.json.History;
import sample.utils.alerts.AlertsUtil;
import sample.utils.requests.RequestsUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Controller for managing historyPage.fxml form
 *
 * @author  Artem Bakanov aka Attilene
 */
public class HistoryPageController {
    /**
     * TableView contained list of user`s translate history
     */
    @FXML
    private TableView<History> historyTableView;

    /**
     * TableColumn in historyTableView containing start_string
     * of records of list of user`s translate history
     */
    @FXML
    private TableColumn<History, String> startStringColumn;

    /**
     * TableColumn in historyTableView containing end_string
     * of records of list of user`s translate history
     */
    @FXML
    private TableColumn<History, String> endStringColumn;

    /**
     * TableColumn in historyTableView containing operation_time
     * of records of list of user`s translate history
     */
    @FXML
    private TableColumn<History, Date> operationTimeColumn;

    /**
     * Window where displaying fxml form
     */
    private Stage dialStage;

    /**
     * List which containing records of user`s translate history
     */
    private ObservableList<History> historiesData;

    /**
     * Method for delete history button for deleting record of user`s translate history
     */
    @FXML
    private void handleDeleteHistory() {
        int selectedIndex = historyTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            RequestsUtil requestsUtil = new RequestsUtil("/history", "DELETE");
            requestsUtil.setParams(new HashMap<>() {{
                put("id", historyTableView.getItems().get(selectedIndex).getId().toString());
            }});
            requestsUtil.thread.start();
            RequestsUtil.runningThread(requestsUtil, dialStage);
            if (Objects.equals(requestsUtil.getResponse(), "history_delete_success"))
                historyTableView.getItems().remove(selectedIndex);
            else if (!requestsUtil.getDisconnect()
                    && Objects.equals(requestsUtil.getResponse(), "history_delete_failed"))
                AlertsUtil.showInternalServerErrorAlert(dialStage);
        }
        else { AlertsUtil.showNotSelectedHistory(dialStage); }
    }

    /**
     * Saving records of user`s translate history to TXT file
     */
    @FXML
    public void handleSaveTXT() {
        File file = createFileChooser("txt");
        if (file != null) {
            try {
                Writer writer = new FileWriter(file.getAbsolutePath(), StandardCharsets.UTF_8);
                writer.write("Текст для перевода    Результат перевода    Дата перевода\n");
                writer.flush();
                for (History history: historiesData) {
                    writer.write(
                            history.getStart_string() + "    " +
                            history.getEnd_string() + "    " +
                            history.getOperation_time() + "\n"
                    );
                    writer.flush();
                }
                writer.close();
            } catch (IOException e) { System.out.println("Не удалось создать файл: " + file.getName()); }
        }
    }

    /**
     * Saving records of user`s translate history to CSV file
     */
    @FXML
    public void handleSaveCSV() {
        File file = createFileChooser("csv");
        if (file != null) {
            try {
                Writer writer = new FileWriter(file.getAbsolutePath(), StandardCharsets.UTF_8);
                CSVWriter csvWriter = new CSVWriter(writer);
                csvWriter.writeNext(new String[] { "Текст для перевода", "Результат перевода", "Дата перевода" });
                for (History history: historiesData) {
                    csvWriter.writeNext(new String[] {
                            history.getStart_string(),
                            history.getEnd_string(),
                            history.getOperation_time().toString()
                    });
                }
                writer.close();
            } catch (IOException e) { System.out.println("Не удалось создать файл: " + file.getName()); }
        }
    }

    /**
     * Close the application window
     */
    @FXML
    private void handleCancel() { dialStage.close(); }

    /**
     * Adding records of user`s translate history to the TableView object
     */
    public void showHistoryContent() {
        startStringColumn.setCellValueFactory(new PropertyValueFactory<>("start_string"));
        endStringColumn.setCellValueFactory(new PropertyValueFactory<>("end_string"));
        operationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("operation_time"));
        historyTableView.setItems(historiesData);
    }

    /**
     * Method for creating file chooser object for saving
     * list of records of user`s translate history
     *
     * @param   suffix  the suffix of the file name that defines the type of data to be saved
     * @return          new FileChooser object
     */
    private File createFileChooser(String suffix) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранение документа");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                suffix.toUpperCase() + " files (*." + suffix + ")",
                "*." + suffix
        );
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showSaveDialog(dialStage);
    }

    /**
     * Setter for window`s stage
     *
     * @param  dialStage  window`s stage
     */
    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    /**
     * Setter for list of records of user`s translate history
     *
     * @param  histories  list of records of user`s translate history
     */
    public void setHistories(History[] histories) {
        historiesData = FXCollections.observableArrayList();
        historiesData.addAll(Arrays.asList(histories));
    }
}
