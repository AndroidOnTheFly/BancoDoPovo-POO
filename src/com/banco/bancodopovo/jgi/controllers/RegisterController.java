package com.banco.bancodopovo.jgi.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField passInput;
    @FXML
    private TextField confirmPassInput;
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
    private Button signUpBt;

}

