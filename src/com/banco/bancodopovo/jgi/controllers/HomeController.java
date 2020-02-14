package com.banco.bancodopovo.jgi.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    private int defaultWidth = 744;
    private int defaultHeight = 543;
    private int maxWidth = 780;
    private int maxHeight = 560;

    @FXML
    private Button signUpButton;

    @FXML
    void goToSignUp(ActionEvent event) throws IOException {

        Parent registerView = FXMLLoader.load(getClass().getResource("../telas/Register.fxml"));
        Scene registerScene = new Scene(registerView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setMaxWidth(defaultWidth);
        window.setMaxHeight(defaultHeight);
        window.setMaxHeight(maxHeight);
        window.setMaxWidth(maxWidth);

        window.setScene(registerScene);
        window.show();
    }

}




