package sample.controllers;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * Controller for managing aboutPage.fxml form
 *
 * @author  Artem Bakanov aka Attilene
 */
public class AboutPageController {
    /**
     * Web link for opening author`s VK page
     */
    private static final Hyperlink VK = new Hyperlink("https://vk.com/attilene");

    /**
     * Web link for opening author`s Instagram page
     */
    private static final Hyperlink INSTAGRAM = new Hyperlink("https://www.instagram.com/artembakanov/");

    /**
     * Web link for opening author`s Telegram chat
     */
    private static final Hyperlink TELEGRAM = new Hyperlink("https://t.me/attilene");

    /**
     * Web link for opening author`s Github page
     */
    private static final Hyperlink GITHUB = new Hyperlink("https://github.com/Attilene");

    /**
     * Window where displaying fxml form
     */
    private Stage dialStage;

    /**
     * Web-link service
     */
    private HostServices hostServices;

    /**
     * Method for opening author`s VK page
     */
    @FXML
    public void openVKLink() { hostServices.showDocument(VK.getText()); }

    /**
     * Method for opening author`s Instagram page
     */
    @FXML
    public void openInstagramLink() { hostServices.showDocument(INSTAGRAM.getText()); }

    /**
     * Method for opening author`s Telegram chat
     */
    @FXML
    public void openTelegramLink() { hostServices.showDocument(TELEGRAM.getText()); }

    /**
     * Method for opening author`s Github page
     */
    @FXML
    public void openGithubLink() { hostServices.showDocument(GITHUB.getText()); }

    /**
     * Close the application window
     */
    @FXML
    public void handleBackAction() { dialStage.close(); }

    /**
     * Setter for web-link service
     *
     * @param  hostServices  service for opening web links
     */
    public void setHostServices(HostServices hostServices) { this.hostServices = hostServices ; }

    /**
     * Setter for window`s stage
     *
     * @param  dialStage  window`s stage
     */
    public void setDialStage(Stage dialStage) { this.dialStage = dialStage; }
}
