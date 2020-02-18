package com.banco.bancodopovo.jgi.controllers;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MenuController {



    /**
     * Classe responsável ser o controlador da cena de interface "Menu"
     * Todos seus componentes e eventos são tratados aqui
     * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
     */
    private static String viewPath;
    @FXML
    private Button signUpButton;
    @FXML
    private Button homeButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button aboutButton;
    @FXML
    private Button contactButton;

    /** Método responsável por redirecionar o usuário para a tela de registro */
    @FXML
    void goToSignUp(ActionEvent event) throws IOException {
        WindowController.setSceneInWindow(event,"../telas/Register.fxml");
    }
    /** Método responsável por redirecionar o usuário para a tela inicial home */
    @FXML
    void goBackToHome(ActionEvent event) throws IOException {

        if(PanelController.currentUser != null)
            viewPath = "../telas/HomeL.fxml";
        else viewPath = "../telas/Home.fxml";
        WindowController.setSceneInWindow(event,viewPath);
    }
    /** Método responsável por redirecionar o usuário para a tela de login */
    @FXML
    void goToLogin(ActionEvent event) throws IOException {
        WindowController.setSceneInWindow(event,"../telas/login.fxml");
    }
    /** Método responsável por redirecionar o usuário para a tela sobre */
    @FXML
    void goToAbout(ActionEvent event) throws IOException {
        if(PanelController.currentUser != null)
            viewPath = "../telas/SobreL.fxml";
        else viewPath = "../telas/Sobre.fxml";
        WindowController.setSceneInWindow(event,viewPath);
    }
    /** Método responsável por redirecionar o usuário para a tela de contato */
    @FXML
    void goToContact(ActionEvent event) throws IOException {
        if(PanelController.currentUser != null){
            viewPath = "../telas/contactL.fxml";
        } else {
            viewPath = "../telas/contact.fxml";
        }
         WindowController.setSceneInWindow(event,viewPath);
    }
    /** Método responsável por redirecionar o usuário para a tela de painel */
    @FXML
    void goToPainel(ActionEvent event) throws IOException {
        Scene scene = WindowController.setSceneInWindow(event,"../telas/painel.fxml");
        PanelController.currentScene = "painel";
        PanelController.setUserInfos(scene);
    }

}
