package sample.controllers;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class AboutPageController {
    @FXML
    private static final Hyperlink VK = new Hyperlink("https://vk.com/attilene");
    @FXML
    private static final Hyperlink INSTAGRAM = new Hyperlink("https://www.instagram.com/artembakanov/");
    @FXML
    private static final Hyperlink TELEGRAM = new Hyperlink("https://t.me/attilene");
    @FXML
    private static final Hyperlink GITHUB = new Hyperlink("https://github.com/Attilene");

    private Stage dialStage;
    private HostServices hostServices;

    public HostServices getHostServices() { return hostServices ; }

    public void setHostServices(HostServices hostServices) { this.hostServices = hostServices ; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    public void openVKLink() { hostServices.showDocument(VK.getText()); }

    @FXML
    public void openInstagramLink() { hostServices.showDocument(INSTAGRAM.getText()); }

    @FXML
    public void openTelegramLink() { hostServices.showDocument(TELEGRAM.getText()); }

    @FXML
    public void openGithubLink() { hostServices.showDocument(GITHUB.getText()); }

    @FXML
    public void handleBackAction() { dialStage.close(); }
}
