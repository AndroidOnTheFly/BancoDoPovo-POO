package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.io.IOException;


public class PanelController {

    @FXML
    private AnchorPane painelScene;
    @FXML
    private Button transfButton;

    @FXML
    private Button SacarButton;

    @FXML
    private Button poupancaButton;

    @FXML
    private Button EmprestimoButton;

    @FXML
    private Button myAccountButton;

    @FXML
    private Button DepositarButton;

    @FXML
    private Button DividasButton;

    @FXML
    private Button sairButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label contaLabel;

    @FXML
    private Label agenciaLabel;

    @FXML
    private Label saldoLabel;

    public static Usuario currentUser;

    public static void setUserInfos(Scene scene){
        Label nameLabel = (Label) scene.lookup("#nameLabel");
        nameLabel.setText(currentUser.getNome());
    }

}
