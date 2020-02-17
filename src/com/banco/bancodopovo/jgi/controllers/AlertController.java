package com.banco.bancodopovo.jgi.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AlertController {


    @FXML
    private Button alertButton;
    @FXML
    private Label errorMessage;

    @FXML
    void alertClick(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    public static void alertMessage(String message,String title) throws IOException {

        Parent alertView = FXMLLoader.load(AlertController.class.getResource("/com/banco/bancodopovo/jgi/telas/alert.fxml"));

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Scene alertScene = new Scene(alertView);
        Label messageLabel = (Label) alertScene.lookup("#errorMessage");
        messageLabel.setText(message);


        window.setScene(alertScene);

        window.showAndWait();

    }

}
