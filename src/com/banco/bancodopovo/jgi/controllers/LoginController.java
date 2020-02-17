package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.banco.HandleInteractions;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.Conta;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passInput;

    @FXML
    void loginAutenticate(ActionEvent event) throws IOException {
        Usuario currentUser = new UsuarioDaoBanco().getUsuarioBy(emailInput.getText(),"email");
        if(currentUser != null){
            if(currentUser.getSenha().equals(passInput.getText())){
                //o usuário pode logar
                Scene panelScene = WindowController.setSceneInWindow(event,"../telas/painel.fxml");

                Map<String,Conta> contaMap = HandleInteractions.pegarContas(currentUser);

                PanelController.currentUser = currentUser;
                PanelController.tipoConta = HandleInteractions.tipoDeContaUsuario(contaMap);

                if(PanelController.tipoConta == 1)
                    PanelController.contaCorrente = (ContaCorrente)contaMap.get("corrente");
                else if(PanelController.tipoConta == 2)
                    PanelController.contaPoupanca = (ContaPoupanca)contaMap.get("poupanca");
                else if(PanelController.tipoConta == 3){
                    PanelController.contaCorrente = (ContaCorrente)contaMap.get("corrente");
                    PanelController.contaPoupanca = (ContaPoupanca)contaMap.get("poupanca");
                }

                PanelController.setUserInfos(panelScene);

            }else{
                AlertController.alertMessage("Email ou senha incorretos! tente novamente!","Erro ao logar");
            }
        }
    }



}
