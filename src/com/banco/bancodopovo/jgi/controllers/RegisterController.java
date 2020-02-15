package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Date;



public class RegisterController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passInput;
    @FXML
    private PasswordField confirmPassInput;
    @FXML
    private TextField cpfInput;
    @FXML
    private TextField estadoInput;
    @FXML
    private TextField cityInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private CheckBox ccBox;
    @FXML
    private CheckBox cpBox;
    @FXML
    void goBackToHome(MouseEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("../telas/Home.fxml"));
        Scene registerScene = new Scene(homeView);
        Stage homeWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homeWindow.setScene(registerScene);
        homeWindow.show();

    }
    @FXML
    void register(ActionEvent event) throws IOException {

        String name = nameInput.getText();
        String cpf = cpfInput.getText();
        String email = emailInput.getText();
        String pass = passInput.getText();
        String confirmPass = confirmPassInput.getText();
        String date = dateInput.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String city = cityInput.getText();
        String estado = estadoInput.getText();

    }
}



