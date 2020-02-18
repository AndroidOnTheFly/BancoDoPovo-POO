package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.dao.ContaPoupancaDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.validations.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Classe responsável por ser a classe controladora da interface Transferencia
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class TransferenciaController {

    @FXML
    private Button transferirButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cpfInput;
    @FXML
    private TextField valorTransferenciaInput;

    @FXML
    private ChoiceBox tipoContaSelect;

    @FXML
    private TextField contaInput;

    @FXML
    private TextField agenciaInput;

    @FXML
    private Label saldoLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label agenciaLabel;

    @FXML
    private Label contaLabel;
    /** método responsável por sair da tela de transferência e redirecionar o usuário para o painel. */
    @FXML
    void sair(ActionEvent event) throws IOException{
        Scene scene = WindowController.setSceneInWindow(event,"../telas/painel.fxml");
        PanelController.setUserInfos(scene);
    }
    /** método responsável por realizar a operação de transferência */
    @FXML
    void transferir(ActionEvent event) throws IOException,RuntimeException {

        int tipoContaAtiva = PanelController.tipoContaAtiva;

        String cpf = cpfInput.getText();
        String agenciadestino = agenciaInput.getText();
        String contadestino = contaInput.getText();
        double valor;


        try{
            boolean sucesso = false;
            valor = Double.parseDouble(valorTransferenciaInput.getText());
            boolean isCpf = Validations.isCPF(cpf);

            if(!isCpf) {
                AlertController.alertMessage("CPF incorreto, tente novamente.","Erro em CPF");
            }else{
                boolean podeTransferir = true;

                if(PanelController.tipoContaAtiva == 1){
                    if(PanelController.contaCorrente.getSaldo() < valor){
                        podeTransferir = false;
                        AlertController.alertMessage("saldo insuficiente","erro ao transferir");
                    }
                }else if(PanelController.tipoContaAtiva == 2){
                    if(PanelController.contaPoupanca.getSaldo() < valor){
                        podeTransferir = false;
                        AlertController.alertMessage("saldo insuficiente","erro ao transferir");
                    }
                }

                if(podeTransferir){
                    ContaCorrente destinoCorrente = (ContaCorrente) new ContaCorrenteDaoBanco().getContaByCpf(cpf);
                    ContaPoupanca destinoPoupanca = (ContaPoupanca) new ContaPoupancaDaoBanco().getContaByCpf(cpf);

                    if(agenciadestino.equals(destinoCorrente.getAgencia()) && contadestino.equals(destinoCorrente.getNumContaCorrent())){

                        sucesso = true;

                        ContaCorrenteDaoBanco contaCorrenteDao = new ContaCorrenteDaoBanco();

                        boolean transferido = false;
                        if(tipoContaAtiva == 1){
                            PanelController.contaCorrente.transferir(destinoCorrente,valor);
                            transferido = contaCorrenteDao.updateConta(PanelController.contaCorrente,PanelController.contaCorrente.getSaldo());
                        }else if(tipoContaAtiva == 2){
                            ContaPoupancaDaoBanco contaPoupancaDao = new ContaPoupancaDaoBanco();
                            PanelController.contaPoupanca.transferir(destinoCorrente,valor);
                            transferido = contaPoupancaDao.updateConta(PanelController.contaPoupanca,PanelController.contaPoupanca.getSaldo());
                        }
                        boolean recebido = contaCorrenteDao.updateConta(destinoCorrente,destinoCorrente.getSaldo());

                        if(transferido && recebido){
                            AlertController.alertMessage("Transferencia feita com exito!","Sucesso");
                        }else{
                            AlertController.alertMessage("Ocorreu um erro ao tentar transferir! tente novamente.","Erro");
                        }

                    }else if(agenciadestino.equals(destinoPoupanca.getAgencia()) && contadestino.equals(destinoPoupanca.getNumContaPoupanca())){

                        sucesso = true;
                        ContaPoupancaDaoBanco contaPoupancaDao = new ContaPoupancaDaoBanco();
                        boolean transferido = false;

                        if(tipoContaAtiva == 1){
                            ContaCorrenteDaoBanco contaCorrenteDao = new ContaCorrenteDaoBanco();
                            PanelController.contaCorrente.transferir(destinoPoupanca,valor);
                            transferido = contaCorrenteDao.updateConta(PanelController.contaCorrente,PanelController.contaCorrente.getSaldo());

                        }else if(tipoContaAtiva == 2){
                            PanelController.contaPoupanca.transferir(destinoPoupanca,valor);
                            transferido = contaPoupancaDao.updateConta(PanelController.contaPoupanca,PanelController.contaPoupanca.getSaldo());
                        }
                        boolean recebido = contaPoupancaDao.updateConta(destinoPoupanca,destinoPoupanca.getSaldo());

                        if(transferido && recebido){
                            AlertController.alertMessage("Transferencia feita com exito!","Sucesso");
                        }else{
                            AlertController.alertMessage("Ocorreu um erro ao tentar transferir! tente novamente.","Erro");
                        }
                    }
                    else{
                        AlertController.alertMessage("Agência ou conta de destino incorretos!","Erro ao transferir");
                    }

                    if(sucesso){
                        if(PanelController.tipoContaAtiva == 1) saldoLabel.setText("Saldo: R$"+PanelController.contaCorrente.getSaldo());
                        else saldoLabel.setText("Saldo: R$" + PanelController.contaPoupanca.getSaldo());
                    }

                }



            }

        }catch(RuntimeException e){
            valorTransferenciaInput.setText("0");
        }
    }

}