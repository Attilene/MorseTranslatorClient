package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controller.EnterPageController;
import sample.controller.MainPageController;
import sample.controller.RegistrationPageController;
import sample.model.Person;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main() {
        // TODO: Добавить веб-запрос на получение данных клиентов
    }

    public BorderPane getRootLayout() { return rootLayout; }

    public Stage getPrimaryStage() { return primaryStage; }

    public ObservableList<Person> getPersonData() { return personData; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Переводчик Морзе");
        initRootLayout();
        showMainPage();
    }

    public void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void showMainPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/mainPage.fxml"));
        AnchorPane mainPage = (AnchorPane) loader.load();
        rootLayout.setCenter(mainPage);
        MainPageController controller = loader.getController();
        controller.setMainApp(this);
    }

    public boolean showEnterPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/enterPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Вход в личный кабинет");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(primaryStage);
            dialStage.setResizable(false);
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            EnterPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            controller.setMain(this);
            dialStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/registrationPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Регистрация пользователя");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(primaryStage);
            dialStage.setResizable(false);
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            RegistrationPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            controller.setMain(this);
            dialStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showTranslatorPage() {
        return true;
    }

    public boolean showAboutPage() {
        return true;
    }

    public boolean showEditPersonPage() {
        return true;
    }

    public static void main(String[] args) { launch(args); }
}