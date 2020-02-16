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
import com.banco.bancodopovo.jgi.banco.ConFactory;



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

        if(!nameValidation || !emailValidation || passValidation != 1 || cpfValidation != 0 || dateValidation == null){
            (new AlertController()).alertMessage("Erro ao efetuar cadastro!\nVerifique os campos:\n" + (!nameValidation ? "Nome," : "") + (!emailValidation ? "Email," : "")
            + ((passValidation == 0 || passValidation == 2) ? "Senha," : "") + (cpfValidation != 0 ? "Cpf," : "")
            + (dateValidation == null ? "Data de nascimento" : ""));
        }else{
            (new AlertController()).alertMessage("Cadastro realizado com sucesso!");
            ConFactory connection = new ConFactory();

        }


    }
}



