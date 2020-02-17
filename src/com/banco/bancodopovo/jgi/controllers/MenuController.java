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


    private static String viewPath;
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
        WindowController.setSceneInWindow(event,"../telas/Register.fxml");
    }

    @FXML
    void goBackToHome(ActionEvent event) throws IOException {

        if(PanelController.currentUser != null)
            viewPath = "../telas/HomeL.fxml";
        else viewPath = "../telas/Home.fxml";
        WindowController.setSceneInWindow(event,viewPath);
    }

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        WindowController.setSceneInWindow(event,"../telas/login.fxml");
    }
    @FXML
    void goToAbout(ActionEvent event) throws IOException {
        if(PanelController.currentUser != null)
            viewPath = "../telas/SobreL.fxml";
        else viewPath = "../telas/Sobre.fxml";
        WindowController.setSceneInWindow(event,viewPath);
    }
    @FXML
    void goToContact(ActionEvent event) throws IOException {
        if(PanelController.currentUser != null){
            viewPath = "../telas/contactL.fxml";
        } else {
            viewPath = "../telas/contact.fxml";
        }
         WindowController.setSceneInWindow(event,viewPath);
    }

    @FXML
    void goToPainel(ActionEvent event) throws IOException {
        Scene scene = WindowController.setSceneInWindow(event,"../telas/painel.fxml");
        PanelController.currentScene = "painel";
        PanelController.setUserInfos(scene);
    }

}
