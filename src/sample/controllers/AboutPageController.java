package sample.controllers;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class AboutPageController {
    @FXML
    private Hyperlink vkLink;
    @FXML
    private Hyperlink instagramLink;
    @FXML
    private Hyperlink telegramLink;
    @FXML
    private Hyperlink githubLink;

    private Stage dialStage;
    private HostServices hostServices ;

    public HostServices getHostServices() { return hostServices ; }

    public void setHostServices(HostServices hostServices) { this.hostServices = hostServices ; }

    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }

    @FXML
    public void initialize() {
        vkLink = new Hyperlink("https://vk.com/attilene");
        instagramLink = new Hyperlink("https://www.instagram.com/artembakanov/");
        telegramLink = new Hyperlink("https://t.me/attilene");
        githubLink = new Hyperlink("https://github.com/Attilene");
    }

    @FXML
    public void openVKLink() { hostServices.showDocument(vkLink.getText()); }

    @FXML
    public void openInstagramLink() { hostServices.showDocument(instagramLink.getText()); }

    @FXML
    public void openTelegramLink() { hostServices.showDocument(telegramLink.getText()); }

    @FXML
    public void openGithubLink() { hostServices.showDocument(githubLink.getText()); }

    @FXML
    public void handleBackAction() { dialStage.close(); }
}
