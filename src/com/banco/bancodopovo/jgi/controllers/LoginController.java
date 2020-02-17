package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.interfaceInteractions.UserConnectionInteraction;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.io.IOException;

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
                Scene panelScene = new WindowController().setSceneInWindow(event,"../telas/painel.fxml");
                PanelController.currentUser = currentUser;
                //HomeController.currentUser = currentUser;
                PanelController.setUserInfos(panelScene);

            }
        }
    }



}
