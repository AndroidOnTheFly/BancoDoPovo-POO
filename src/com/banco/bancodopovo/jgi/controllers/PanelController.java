package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.banco.HandleInteractions;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.Conta;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
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

    public static String currentScene = "painel";
    public static Usuario currentUser = null;
    public static ContaCorrente contaCorrente = null;
    public static ContaPoupanca contaPoupanca = null;
    public static int tipoContaAtiva = 1;
    public static int tipoConta = 0;


    @FXML
    void transferView(ActionEvent event) throws IOException{
        Scene scene =  WindowController.setSceneInWindow(event,"../telas/Transferencia.fxml");
        currentScene = "transferir";
        setUserInfos(scene);
    }

    @FXML
    void depositarView(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Depositar.fxml");
        currentScene = "depositar";
        setUserInfos(scene);
    }

    @FXML
    void sacarView(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Sacar.fxml");
        currentScene = "sacar";
        setUserInfos(scene);
    }
    @FXML
    void goToMyAccount(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Perfil.fxml");
        currentScene = "MinhaConta";
        //setUserInfos(scene);
    }
    @FXML
    private static void setPoupancaEnable(Scene scene){
        if(tipoConta == 3 && currentScene == "painel"){
            Button poupancaButton = (Button)scene.lookup("#poupancaButton");
            poupancaButton.setDisable(false);
        }
    }
    @FXML
    private static void changePoupancaButtonType(Button poupancaButton){

        if(tipoContaAtiva == 1) {
            poupancaButton.setText("Corrente");
            poupancaButton.setStyle("-fx-background-color:#fdcb6e; -fx-cursor: hand");
            tipoContaAtiva = 2;
        }else if(tipoContaAtiva == 2){
            poupancaButton.setText("Poupança");
            poupancaButton.setStyle("-fx-background-color: #74b9ff; -fx-cursor: hand");
            tipoContaAtiva = 1;
        }
    }

    @FXML
    void mudarTipoContaAtiva (ActionEvent event){

        changePoupancaButtonType(poupancaButton);

        if(tipoContaAtiva == 1){
            agenciaLabel.setText("Agência: " + PanelController.contaCorrente.getAgencia());
            contaLabel.setText("Conta: "+ PanelController.contaCorrente.getNumContaCorrent());
            saldoLabel.setText("Saldo: R$"+PanelController.contaCorrente.getSaldo());
        }else if(tipoContaAtiva == 2){
            agenciaLabel.setText("Agência: " + PanelController.contaPoupanca.getAgencia());
            contaLabel.setText("Conta: "+ PanelController.contaPoupanca.getNumContaPoupanca());
            saldoLabel.setText("Saldo: R$"+PanelController.contaPoupanca.getSaldo());
        }
    }

    @FXML
    public static void setUserInfos(Scene scene){

        Label nameLabel = (Label) scene.lookup("#nameLabel");
        nameLabel.setText(currentUser.getNome());

        Label agenciaLabel =  (Label) scene.lookup("#agenciaLabel");
        Label contaLabel = (Label)scene.lookup("#contaLabel");
        Label saldoLabel = (Label)scene.lookup("#saldoLabel");
        Button poupancaButton = (Button)scene.lookup("#poupancaButton");

        if(tipoContaAtiva == 1) {
            agenciaLabel.setText("Agência: " + PanelController.contaCorrente.getAgencia());
            contaLabel.setText("Conta: "+ PanelController.contaCorrente.getNumContaCorrent());
            saldoLabel.setText("Saldo: R$"+PanelController.contaCorrente.getSaldo());
            if(PanelController.currentScene == "painel"){
                poupancaButton.setText("Poupança");
                poupancaButton.setStyle("-fx-background-color:#74b9ff; -fx-cursor: hand");
            }
        }
        else if(tipoContaAtiva == 2) {
            agenciaLabel.setText("Agência: " + PanelController.contaPoupanca.getAgencia());
            contaLabel.setText("Conta: "+ PanelController.contaPoupanca.getNumContaPoupanca());
            saldoLabel.setText("Saldo: R$"+PanelController.contaPoupanca.getSaldo());
            if(PanelController.currentScene == "painel"){
                poupancaButton.setText("Corrente");
                poupancaButton.setStyle("-fx-background-color:#fdcb6e; -fx-cursor: hand");
            }
        }

        setPoupancaEnable(scene);



    }

}
