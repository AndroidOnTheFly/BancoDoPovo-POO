package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.dao.ContaPoupancaDaoBanco;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;
import com.banco.bancodopovo.jgi.modelo.Conta;
import com.banco.bancodopovo.jgi.validations.Validations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PerfilController {

    @FXML
    private TextField nomeInput;

    @FXML
    private TextField cpfInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField senhaInput;

    @FXML
    private TextField confirmarSenhaInput;

    @FXML
    private TextField agenciaInput;

    @FXML
    private ComboBox cidadeInput;

    @FXML
    private Button deletarContaButton;

    @FXML
    private Button salvarAlteracoesButton;

    @FXML
    private Button cancelButton;
    @FXML
    private TextField contaInput;

    @FXML
    void cancelar(ActionEvent event) {

    }
    @FXML
    void sair(ActionEvent event) throws IOException{
        WindowController.setSceneInWindow(event,"../telas/painel.fxml");
    }
    @FXML
    void deletarConta(ActionEvent event) throws IOException {
        int deletUser = AlertController.alertDelete("Tem certeza que quer deletar sua conta?","Deletar conta");
        if(deletUser == 1){

            boolean deleteContaCorrente = false;
            boolean deleteContaPoupanca = false;

            if(PanelController.tipoConta == 1){
                deleteContaCorrente = (new ContaCorrenteDaoBanco().deleteConta(PanelController.contaCorrente));
            }else if(PanelController.tipoConta == 2){
                deleteContaPoupanca = (new ContaPoupancaDaoBanco().deleteConta(PanelController.contaPoupanca));
            }else if(PanelController.tipoConta == 3){
                deleteContaCorrente = (new ContaCorrenteDaoBanco().deleteConta(PanelController.contaCorrente));
                deleteContaPoupanca = (new ContaPoupancaDaoBanco().deleteConta(PanelController.contaPoupanca));
            }

            boolean response = (new UsuarioDaoBanco().deleteUsuario(PanelController.currentUser));

            if(response && (deleteContaCorrente || deleteContaPoupanca) ){
                AlertController.alertMessage("A sua conta foi deletada. :(","Deletado");
                WindowController.setSceneInWindow(event,"../telas/Home.fxml");
            }else{
                AlertController.alertMessage("Ocorreu um erro ao tentar deletar sua conta","Erro");
            }
        }
    }

    @FXML
    void salvarAlteracoes(ActionEvent event) throws IOException {

        String novoNome = nomeInput.getText();
        String novoEmail = emailInput.getText();
        String novaSenha = senhaInput.getText();
        String novaSenhaConfirmada = confirmarSenhaInput.getText();
        String cidadeNova = (String) cidadeInput.getValue();

        if(!Validations.validarEmail(novoEmail) || !Validations.validarNome(novoNome) || Validations.validarSenha(novaSenha,novaSenhaConfirmada) != 1 || cidadeNova == null){
            AlertController.alertMessage("Erro ao salvar as mudanças, verifique os dados","Erro ao alterar a conta");
        }else {
            PanelController.currentUser.setNome(novoNome);
            PanelController.currentUser.setEmail(novoEmail);
            PanelController.currentUser.setSenha(novaSenha);
            PanelController.currentUser.setCidade(Validations.validarCidade(cidadeNova));

            boolean updated = (new UsuarioDaoBanco().updateUsuario(PanelController.currentUser));

            if(updated){
                AlertController.alertMessage("Conta alterada com sucesso!","Sucesso");
                Scene scene = WindowController.setSceneInWindow(event,"../telas/painel.fxml");
                PanelController.setUserInfos(scene);
            }else{
                AlertController.alertMessage("Erro ao tentar fazer alterações, tente novamente","Erro ao alterar a conta");
            }
        }
    }

    @FXML
    private void initialize(){
        Usuario currentUser = PanelController.currentUser;

        if(PanelController.tipoContaAtiva == 1){
            contaInput.setPromptText("Agência: "+ PanelController.contaCorrente.getNumContaCorrent());
        }else {
            contaInput.setPromptText("Agência: "+ PanelController.contaPoupanca.getNumContaPoupanca());
        }
        nomeInput.setPromptText("nome: "+currentUser.getNome());
        emailInput.setPromptText("email: "+currentUser.getEmail());
        cpfInput.setPromptText("cpf: "+currentUser.getCpf());

        cidadeInput.getItems().addAll(Cidade.Cajazeiras.name(),Cidade.JocaClaudino.name(),Cidade.Uiraúna.name());
    }

    @FXML
    private void mudarAgencia(){
        String cidade = (String) cidadeInput.getValue();
        if(cidade.equals(Cidade.Cajazeiras.name())){
            agenciaInput.setPromptText("Agência: "+ Cidade.Cajazeiras.getAgencia());
        }else if(cidade.equals(Cidade.JocaClaudino.name())){
            agenciaInput.setPromptText("Agência: "+ Cidade.JocaClaudino.getAgencia());
        }else if(cidade.equals(Cidade.Uiraúna.name())){
            agenciaInput.setPromptText("Agência: "+ Cidade.Uiraúna.getAgencia());
        }

    }


}
