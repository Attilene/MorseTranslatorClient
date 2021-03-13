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
import sample.utils.AlertsUtil;
import sample.utils.RequestsUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HistoryPageController {
    @FXML
    private TableView<History> historyTableView;
    @FXML
    private TableColumn<History, String> startStringColumn;
    @FXML
    private TableColumn<History, String> endStringColumn;
    @FXML
    private TableColumn<History, Date> operationTimeColumn;

    private Stage dialStage;
    private ObservableList<History> historiesData;

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    public void setHistories(History[] histories) {
        historiesData = FXCollections.observableArrayList();
        historiesData.addAll(Arrays.asList(histories));
    }

    public void showHistoryContent() {
        startStringColumn.setCellValueFactory(new PropertyValueFactory<>("start_string"));
        endStringColumn.setCellValueFactory(new PropertyValueFactory<>("end_string"));
        operationTimeColumn.setCellValueFactory(new PropertyValueFactory<>("operation_time"));
        historyTableView.setItems(historiesData);
    }

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

    @FXML
    private void handleCancel() { dialStage.close(); }

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
}
