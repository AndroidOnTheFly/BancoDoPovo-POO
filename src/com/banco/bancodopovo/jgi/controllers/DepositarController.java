package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.dao.ContaPoupancaDaoBanco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class DepositarController {
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
    private Button depositarButton;

    @FXML
    private ComboBox tipoContaSelect;

    @FXML
    private TextField valorDepositoInput;

    @FXML
    void depositar(ActionEvent event) throws IOException,RuntimeException {

        double novoSaldo,valorDepositado;

        try{
            valorDepositado = Double.parseDouble(valorDepositoInput.getText());

                if(PanelController.tipoContaAtiva == 1) {

                    PanelController.contaCorrente.depositar(valorDepositado);
                    novoSaldo = PanelController.contaCorrente.getSaldo();

                    boolean success = new ContaCorrenteDaoBanco().updateConta(PanelController.contaCorrente,novoSaldo);

                    if(success){
                        AlertController.alertMessage("Deposito feito com sucesso!","Sucesso");
                        PanelController.contaCorrente.setSaldo(novoSaldo);
                        saldoLabel.setText("Saldo: R$"+ novoSaldo);
                    }
                    else
                        AlertController.alertMessage("Ocorreu um erro ao efetuar o deposito, " +
                                "tente novamente.","Erro ao depositar");
                }
                else if(PanelController.tipoContaAtiva == 2){

                    PanelController.contaPoupanca.depositar(valorDepositado);
                    novoSaldo = PanelController.contaPoupanca.getSaldo();

                    boolean success = new ContaPoupancaDaoBanco().updateConta(PanelController.contaPoupanca,novoSaldo);

                    if(success) {
                        AlertController.alertMessage("Deposito feito com sucesso! Você ganhou ua taxa do total do depósito!", "Sucesso");
                        PanelController.contaPoupanca.setSaldo(novoSaldo);
                        saldoLabel.setText("Saldo: R$"+ novoSaldo);
                    }
                    else
                        AlertController.alertMessage("Ocorreu um erro ao efetuar o deposito, " +
                                "tente novamente.","Erro ao depositar");
                }
        }catch(RuntimeException e){
            valorDepositoInput.setText("0");
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
