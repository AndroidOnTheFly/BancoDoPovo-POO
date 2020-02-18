package com.banco.bancodopovo.jgi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe que armazena métodos para gerar uma janela de alerta.
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class AlertController {

    @FXML
    private Button alertButton;
    @FXML
    private Label errorMessage;

    @FXML
    /**Método que espera por um evento de clique no alerta, indicando que ele deve ser fechado*/
    void alertClick(ActionEvent event) throws IOException {
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }
    /**Método que gera a janela de alerta */
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
    /**Método responsável por gerar o segundo tipo de janela de alerta, de tipo delete */
    @FXML
    public static int alertDelete(String message, String title) throws IOException{

        AtomicInteger deleted = new AtomicInteger();

        Parent alertView = FXMLLoader.load(AlertController.class.getResource("/com/banco/bancodopovo/jgi/telas/alertDelete.fxml"));

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Scene alertScene = new Scene(alertView);
        Button deleteButton = (Button) alertScene.lookup("#deleteButton");
        Button cancelButton = (Button) alertScene.lookup("#cancelButton");

        deleteButton.setOnAction(e -> {
            deleted.set(1);
            window.close();
        });

        cancelButton.setOnAction(e -> {
            deleted.set(0);
            window.close();
        });
        window.setScene(alertScene);

        window.showAndWait();

        return deleted.get();
    }

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;

    @FXML
    void cancel(ActionEvent event) {

    }
    @FXML
    /**Método que espera por um evento de clique no alerta do tipo delete, chamado quando um usuário requisita
     * o deletamento de sua conta*/
    void delete(ActionEvent event) throws IOException {
        PanelController.currentUser = null;
    }

}
