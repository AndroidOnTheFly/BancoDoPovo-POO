package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import com.banco.bancodopovo.jgi.validations.Validations;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;


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

        String cc = ccBox.isSelected() ? ccBox.getText() : "";
        String cp = cpBox.isSelected() ? cpBox.getText() : "";

        //tratando o input do usuário
        Boolean nameValidation = Validations.validarNome(name);
        Boolean emailValidation = Validations.validarEmail(email);
        int passValidation = Validations.validarSenha(pass,confirmPass);
        boolean cpfValidation =  Validations.isCPF(cpf);
        String dateValidation = Validations.validarDate(date);
        Cidade cidade = Validations.validarCidade(city);
        TipoConta tipo = Validations.validarTipoConta(cc,cp);

        if(!nameValidation || !emailValidation || passValidation != 1 || !cpfValidation || dateValidation == null || city == null){
            (new AlertController()).alertMessage("Erro ao efetuar cadastro!\nVerifique os campos:\n" + (!nameValidation ? "Nome," : "") + (!emailValidation ? "Email," : "")
            + ((passValidation == 0 || passValidation == 2) ? "Senha," : "") + (!cpfValidation ? "Cpf," : "")
            + (dateValidation == null ? "Data de nascimento," : "") + (city == null ? "Cidade," : ""));
        }else if(cc.length() == 0 && cp.length() == 0){
            (new AlertController()).alertMessage("Você deve selecionar pelo menos um tipo de conta!");
        }else{
            UsuarioDaoBanco usuarioDaoBanco = new UsuarioDaoBanco();
            boolean userInserted = usuarioDaoBanco.insertUsuario(
                    new Usuario(name,cpf,email,dateValidation,estadoInput.getText(),
                            cidade,tipo,pass),
                    "");

            if(userInserted) {
                (new AlertController()).alertMessage("Cadastro realizado com sucesso! :D");
            }else{
                (new AlertController()).alertMessage("Ocorreu um erro ao tentar realizar o cadastro! Por favor, tente novamente!");
            }

            //(t)
        }


    }
}



