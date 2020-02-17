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
    private void initialize(){
        int tipoConta = PanelController.tipoContaAtiva;
        if(tipoConta == 1){
            tipoContaSelect.getItems().addAll("corrente");
        }else if(tipoConta == 2){
            tipoContaSelect.getItems().addAll("poupança");
        }

    }

    @FXML
    void depositar(ActionEvent event) throws IOException,RuntimeException {

        String item = (String) tipoContaSelect.getSelectionModel().getSelectedItem();
        double novoSaldo,valorDepositado;

        try{
            valorDepositado = Double.parseDouble(valorDepositoInput.getText());

            if(item == null) {
                AlertController.alertMessage("Você precisa escolher um tipo de conta!","Erro ao depositar");
            }else{
                if(item == "corrente") {
                    novoSaldo = PanelController.contaCorrente.getSaldo() + valorDepositado;
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
                else if(item == "poupança"){

                    novoSaldo = PanelController.contaPoupanca.getSaldo() + valorDepositado;

                    boolean success = new ContaPoupancaDaoBanco().updateConta(PanelController.contaPoupanca,novoSaldo);
                    if(success) {
                        AlertController.alertMessage("Deposito feito com sucesso!", "Sucesso");
                        PanelController.contaPoupanca.setSaldo(novoSaldo);
                        saldoLabel.setText("Saldo: R$"+ novoSaldo);
                    }
                    else
                        AlertController.alertMessage("Ocorreu um erro ao efetuar o deposito, " +
                                "tente novamente.","Erro ao depositar");
                }
            }
        }catch(RuntimeException e){
            valorDepositoInput.setText("0");
        }

    }

}
