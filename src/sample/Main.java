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

/**
 * Class for running the Morse translator client application
 *
 * @author  Artem Bakanov aka Attilene
 * @since   15.0.2
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Main window for displaying project fxml forms
     */
    private Stage primaryStage;

    /**
     * Basic empty constructor
     */
    public Main() {}

    /**
     * Method that is executed first when running a javafx application
     *
     * @param  primaryStage  main window for displaying project fxml forms
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Переводчик Морзе");
        this.primaryStage.getIcons().add(new Image("file:src/sample/resources/images/icon.png"));
        showMainPage(initLayout(this.primaryStage, "views/rootLayout.fxml"));
    }

    /**
     * Method for displaying background border of main window form
     *
     * @param   stage  window for displaying fxml form
     * @param   path   path to fxml file that will be displayed
     * @return         layout of border pane
     */
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

    /**
     * Show main scene of application
     *
     * @param  layout  layout of border pane
     */
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

    /**
     * Show enter scene for log in to private cabinet of application
     *
     * @return  instance of Person model contained private date of a person
     */
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

    /**
     * Show registration scene for sign up to private cabinet of application
     */
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

    /**
     * Show translator scene for managing translations, translate processes and private cabinet
     *
     * @param  person  instance of Person model contained private date of a person
     */
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

    /**
     * Show about scene for calling up summary information
     * about application and author
     */
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

    /**
     * Show edit person scene for managing person data and private cabinet
     *
     * @param   stage   stage which is owner of the current stage
     * @param   person  instance of Person model contained private date of a person
     * @return          instance of EditPersonPageController class
     */
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

    /**
     * Show history scene for bring out user`s history of translations
     *
     * @param  stage      stage which is owner of the current stage
     * @param  histories  user`s history of translations
     */
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

    /**
     * Main method for running the project
     *
     * @param  args  project variables
     */
    public static void main(String[] args) { launch(args); }
}