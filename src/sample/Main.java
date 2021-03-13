package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controllers.*;
import sample.models.app.Person;
import sample.models.json.History;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;

    public Main() {}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Переводчик Морзе");
        this.primaryStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
        showMainPage(initLayout(this.primaryStage, "views/rootLayout.fxml"));
    }

    @FXML
    public BorderPane initLayout(Stage stage, String path) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(path));
            BorderPane layout = loader.load();
            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.setResizable(false);
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            stage.show();
            return layout;
        } catch (IOException e) {
            System.out.println("Не удалось загрузить окно!");
            return null;
        }
    }

    @FXML
    public void showMainPage(BorderPane layout) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/mainPage.fxml"));
            AnchorPane mainPage = loader.load();
            layout.setCenter(mainPage);
            MainPageController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) { System.out.println("Не удалось загрузить окно!"); }
    }

    @FXML
    public Person showEnterPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/enterPage.fxml"));
            AnchorPane page = loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Вход в личный кабинет");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(primaryStage);
            dialStage.setResizable(false);
            dialStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            EnterPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            dialStage.showAndWait();
            return controller.getPerson();
        } catch (IOException e) {
            System.out.println("Не удалось загрузить окно!");
            return null;
        }
    }

    @FXML
    public void showRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/registrationPage.fxml"));
            AnchorPane page = loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Регистрация пользователя");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(primaryStage);
            dialStage.setResizable(false);
            dialStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            RegistrationPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            dialStage.showAndWait();
        } catch (IOException e) { System.out.println("Не удалось загрузить окно!"); }
    }

    @FXML
    public void showTranslatorPage(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/translatorPage.fxml"));
            AnchorPane page = loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Переводчик Морзе");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(primaryStage);
            dialStage.setResizable(false);
            dialStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            TranslatorPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            controller.setPerson(person);
            controller.setMain(this);
            dialStage.showAndWait();
        } catch (IOException e) { System.out.println("Не удалось загрузить окно!"); }
    }

    @FXML
    public void showAboutPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/aboutPage.fxml"));
            AnchorPane page = loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Справка");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(primaryStage);
            dialStage.setResizable(false);
            dialStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            AboutPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            controller.setHostServices(getHostServices());
            dialStage.showAndWait();
        } catch (IOException e) { System.out.println("Не удалось загрузить окно!"); }
    }

    @FXML
    public EditPersonPageController showEditPersonPage(Stage stage, Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/editPersonPage.fxml"));
            AnchorPane page = loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("Настройка личного кабинета");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(stage);
            dialStage.setResizable(false);
            dialStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            EditPersonPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            controller.setPerson(person);
            dialStage.showAndWait();
            return controller;
        } catch (IOException e) {
            System.out.println("Не удалось загрузить окно!");
            return null;
        }
    }

    @FXML
    public void showHistoryPage(Stage stage, History[] histories) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/historyPage.fxml"));
            AnchorPane page = loader.load();
            Stage dialStage = new Stage();
            dialStage.setTitle("История переводов");
            dialStage.initModality(Modality.WINDOW_MODAL);
            dialStage.initOwner(stage);
            dialStage.setResizable(false);
            dialStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
            Scene scene = new Scene(page);
            dialStage.setScene(scene);
            HistoryPageController controller = loader.getController();
            controller.setDialStage(dialStage);
            controller.setHistories(histories);
            controller.showHistoryContent();
            dialStage.showAndWait();
        } catch (IOException e) { System.out.println("Не удалось загрузить окно!"); }
    }

    public static void main(String[] args) { launch(args); }
}