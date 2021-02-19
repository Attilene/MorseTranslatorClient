package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.MainPageController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public Main() {}

    public BorderPane getRootLayout() { return rootLayout; }

    public Stage getPrimaryStage() { return primaryStage; }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Кодировщик Морзе");
        initRootLayout();
        showMainPage();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Group root = new Group();
            root.getChildren().addAll(rootLayout, setLogoImage());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void showMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/mainScene.fxml"));
            AnchorPane mainPage = (AnchorPane) loader.load();
            rootLayout.setCenter(mainPage);
            MainPageController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public ImageView setLogoImage() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/sample/resources/logo_F.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(50);
        imageView.setY(50);
        imageView.setFitHeight(150);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public static void main(String[] args) { launch(args); }
}