package com.banco.bancodopovo.jgi.controllers;
import com.banco.bancodopovo.jgi.controllers.WindowController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {


    @FXML
    private Button signUpButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button aboutButton;
    @FXML
    private Button contactButton;

    @FXML
    void goToSignUp(ActionEvent event) throws IOException {
        new WindowController().setSceneInWindow(event,"../telas/Register.fxml");
    }

    @FXML
    void goBackToHome(ActionEvent event) throws IOException {
        new WindowController().setSceneInWindow(event,"../telas/Home.fxml");
    }

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        new WindowController().setSceneInWindow(event,"../telas/login.fxml");
    }
    @FXML
    void goToAbout(ActionEvent event) throws IOException {
        new WindowController().setSceneInWindow(event, "../telas/Sobre.fxml");
    }
    @FXML
    void goToContact(ActionEvent event) throws IOException {
        new WindowController().setSceneInWindow(event,"../telas/contact.fxml");
    }

}
