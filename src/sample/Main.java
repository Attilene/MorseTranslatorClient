package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private final ObservableList<Person> personData = FXCollections.observableArrayList();

    public Main() {}

    public BorderPane getRootLayout() { return rootLayout; }

    public Stage getPrimaryStage() { return primaryStage; }

    public ObservableList<Person> getPersonData() { return personData; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Переводчик Морзе");
        this.primaryStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
        initRootLayout();
        showMainPage();
    }

    @FXML
    public void initRootLayout() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showMainPage() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/mainPage.fxml"));
            AnchorPane mainPage = (AnchorPane) loader.load();
            rootLayout.setCenter(mainPage);
            MainPageController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Person showEnterPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/enterPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
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
            controller.setMain(this);
            dialStage.showAndWait();
            return controller.getPerson();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    public boolean showRegistrationPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/registrationPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
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
            controller.setMain(this);
            dialStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void showTranslatorPage(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/translatorPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showAboutPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/aboutPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public EditPersonPageController showEditPersonPage(Stage stage, Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/editPersonPage.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
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
            controller.setMain(this);
            controller.setPerson(person);
            dialStage.showAndWait();
            return controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) { launch(args); }
}