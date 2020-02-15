package com.banco.bancodopovo.jgi.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private int defaultWidth = 780;
    private int defaultHeight = 560;
    private int maxWidth = 800;
    private int maxHeight = 584;

    @FXML
    private Button signUpButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button loginButton;

    @FXML
    void goToSignUp(ActionEvent event) throws IOException {
        setSceneInWindow(event,"../telas/Register.fxml");
        //Parent registerView = FXMLLoader.load(getClass().getResource("../telas/Register.fxml"));
        //Scene registerScene = new Scene(registerView);

        //Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        //window.setScene(registerScene);
        //window.show();
    }

    @FXML
    void goBackToHome(ActionEvent event) throws IOException {
        setSceneInWindow(event,"../telas/Home.fxml");
    }

    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        setSceneInWindow(event,"../telas/login.fxml");
    }

    void setSceneInWindow(ActionEvent event, String pathToFxml ) throws IOException{
        Parent view = FXMLLoader.load(getClass().getResource(pathToFxml));
        Scene sceneToShow = new Scene(view);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setMaxWidth(defaultWidth);
        window.setMaxHeight(defaultHeight);
        window.setMaxHeight(maxHeight);
        window.setMaxWidth(maxWidth);
        window.setScene(sceneToShow);
        window.show();
    }

}
