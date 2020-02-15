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
import java.io.IOException;
import java.time.LocalDate;
import com.banco.bancodopovo.jgi.validations.Validations;
import java.time.format.DateTimeFormatter;



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
    private ComboBox selectCity;
    @FXML
    private DatePicker dateInput;
    @FXML
    private CheckBox ccBox;
    @FXML
    private CheckBox cpBox;

    @FXML
    private void initialize(){
        selectCity.getItems().addAll("Cajazeiras","Uiraúna","JocaClaudino");
    }
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
        LocalDate date = dateInput.getValue();
        String city = (String)selectCity.getValue();
        String formattedDate;

        //tratando o input do usuário

        Boolean nameValidation = Validations.validarNome(name);
        Boolean emailValidation = Validations.validarEmail(email);
        int passValidation = Validations.validarSenha(pass,confirmPass);
        int cpfValidation =  Validations.validarCPF(cpf,true);
        String dateValidation = Validations.validarDate(date);

        if(nameValidation){
            System.out.println("Está tudo ok com o nome");
        }

        if(emailValidation) {
            System.out.println("Está tudo ok com o email");
        }

        if(passValidation == 1){
            System.out.println("Está tudo ok com a senha");
        }

        if(cpfValidation == 0){
            System.out.println("Está tudo ok com o cpf");
        }

        if(dateValidation != null){
            System.out.println("Está tudo ok com a data");
        }

        if(city != null) {
            System.out.println("City is ok, city: "+ city);
        }









    }
}



