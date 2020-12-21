package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Classe responsável por ser a classe controladora da interface Painel
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
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

    /** Método responsável por redirecionar o usuário para a tela de transferência */
    @FXML
    void transferView(ActionEvent event) throws IOException{
        Scene scene =  WindowController.setSceneInWindow(event,"../telas/Transferencia.fxml");
        currentScene = "transferir";
        setUserInfos(scene);
    }
    /** Método responsável por redirecionar o usuário para a tela de deposito */
    @FXML
    void depositarView(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Depositar.fxml");
        currentScene = "depositar";
        setUserInfos(scene);
    }
    /** Método responsável por redirecionar o usuário para a tela de saque */
    @FXML
    void sacarView(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Sacar.fxml");
        currentScene = "sacar";
        setUserInfos(scene);
    }
    /** Método responsável por redirecionar o usuário para a tela da conta de usuário */
    @FXML
    void goToMyAccount(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Perfil.fxml");
        currentScene = "MinhaConta";
    }
    /** Método responsável por redirecionar o usuário para a tela de dividas */
    @FXML
    void goToDividas(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Dividas.fxml");
        currentScene = "Dividas";
        setUserInfos(scene);
    }
    /** Método responsável por redirecionar o usuário para a tela de emprestimo */
    @FXML
    void goToEmprestimo(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/Emprestimo.fxml");
        currentScene = "Emprestimo";
        setUserInfos(scene);
    }
    /** Método responsável por permitir a mudança do tipo de conta que está sendo utilizado atualente (poupança/corrente)
     * a mudança é permitida se o usuário possuir uma conta mista. */
    @FXML
    private static void setPoupancaEnable(Scene scene){
        if(tipoConta == 3 && currentScene == "painel"){
            Button poupancaButton = (Button)scene.lookup("#poupancaButton");
            poupancaButton.setDisable(false);
        }
    }
    /**Método responsável por mudar o estado do botão de mudança de conta e a conta atual de um usuário */
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

    /** Método responsável por mudar o tipo de conta que está ativa, para caso o usuário tenha conta mista. */
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
    /** método responsável por sair da tela de painel */
    @FXML
    void sair(ActionEvent event) throws IOException{
        WindowController.setSceneInWindow(event,"../telas/Home.fxml");
        PanelController.currentUser = null;
        PanelController.contaCorrente = null;
        PanelController.contaPoupanca = null;
    }
    /** método responsável por setar os dados de um usuário logado no sistema */
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
