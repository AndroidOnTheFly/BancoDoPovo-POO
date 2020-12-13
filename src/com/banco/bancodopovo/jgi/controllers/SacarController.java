package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.dao.ContaPoupancaDaoBanco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


/**
 * Classe responsável por ser a classe controladora da interface Sacar
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class SacarController {


    @FXML
    private Label nameLabel;

    @FXML
    private Label agenciaLabel;

    @FXML
    private Label contaLabel;

    @FXML
    private Label saldoLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button sacarButton;

    @FXML
    private ComboBox tipoContaSelect;

    @FXML
    private TextField valorSaqueInput;
    /** método responsável por sair da tela de saque, redirecionando o usuário para o painel */
    @FXML
    void sair(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/painel.fxml");
        PanelController.setUserInfos(scene);
    }
    /** método responsável por realizar a operação de saque */
    @FXML
    void sacar(ActionEvent event) throws IOException, RuntimeException {

        double novoSaldo,valorSacado;

        try{
            valorSacado = Double.parseDouble(valorSaqueInput.getText());

                if(PanelController.tipoContaAtiva == 1) {
                    boolean podeSacar =  PanelController.contaCorrente.podeSacar( PanelController.contaCorrente.getSaldo(),valorSacado);

                    if(podeSacar){
                        PanelController.contaCorrente.realizarSaque(valorSacado);
                        novoSaldo = PanelController.contaCorrente.getSaldo();
                        boolean success = new ContaCorrenteDaoBanco().updateConta(PanelController.contaCorrente,novoSaldo);
                        if(success){
                            AlertController.alertMessage("Saque realizado com sucesso!","Sucesso");
                            PanelController.contaCorrente.setSaldo(novoSaldo);
                            saldoLabel.setText("Saldo: R$"+ novoSaldo);
                        }
                        else
                            AlertController.alertMessage("Ocorreu um erro ao efetuar o saque, " +
                                    "tente novamente.","Erro ao sacar");
                    }else{
                        AlertController.alertMessage("Saldo insuficiente para efetuar saque!", "Erro ao sacar");
                    }

                }
                else if(PanelController.tipoContaAtiva == 2){

                    boolean podeSacar = PanelController.contaPoupanca.podeSacar(PanelController.contaPoupanca.getSaldo(),valorSacado);

                    if(podeSacar) {
                        PanelController.contaPoupanca.realizarSaque(valorSacado);
                        novoSaldo = PanelController.contaPoupanca.getSaldo();

                        boolean success = new ContaPoupancaDaoBanco().updateConta(PanelController.contaPoupanca, novoSaldo);
                        if (success) {
                            AlertController.alertMessage("saque feito com sucesso!", "Sucesso");
                            PanelController.contaPoupanca.setSaldo(novoSaldo);
                            saldoLabel.setText("Saldo: R$" + novoSaldo);
                        } else
                            AlertController.alertMessage("Ocorreu um erro ao efetuar o saque, " +
                                    "tente novamente.", "Erro ao sacar");
                    }else{
                        AlertController.alertMessage("Saldo insuficiente para efetuar saque!", "Erro ao sacar");
                    }
                }
            }
        catch(RuntimeException e){
            valorSaqueInput.setText("0");
        }catch(IOException e){
            e.printStackTrace();
        }


    }

}
