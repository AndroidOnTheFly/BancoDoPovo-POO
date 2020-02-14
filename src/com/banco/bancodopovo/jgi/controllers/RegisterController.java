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
import javafx.scene.image.ImageView;

import java.io.IOException;

public class RegisterController {

    @FXML
    private ImageView RegisterGoBack;

    @FXML
    void goBackToHome(MouseEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("../telas/Home.fxml"));
        Scene registerScene = new Scene(homeView);
        Stage homeWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        homeWindow.setScene(registerScene);
        homeWindow.show();
    }

}

