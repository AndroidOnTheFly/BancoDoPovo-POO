package com.banco.bancodopovo.jgi.visao;

import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.EmprestimoSalvarAperto;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;
import com.banco.bancodopovo.jgi.modelo.Conta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;


public class App extends Application{

    private int defaultWidth = 780;
    private int defaultHeight = 560;
    private int maxWidth = 800;
    private int maxHeight = 584;

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        launch(args);
        Usuario usuario = new Usuario("André", "07048150440", "Iarlyson.santana@outlook.com",
                LocalDate.now(), "Paraíba", Cidade.Cajazeiras, TipoConta.Corrente, "123456");

        ContaCorrente contaCorrente = new ContaCorrente(usuario, Cidade.Cajazeiras);
        System.out.println(contaCorrente.getSaldo());
        EmprestimoSalvarAperto emprestimoSalvarAperto = new EmprestimoSalvarAperto();
        emprestimoSalvarAperto.pegarEmprestimo(contaCorrente);
        System.out.println(contaCorrente.getSaldo());
        emprestimoSalvarAperto.pagarPartedeEmprestimo(500, contaCorrente);
        System.out.println(contaCorrente.getSaldo());
        contaCorrente.depositar(500);
        emprestimoSalvarAperto.pagarTotalDeEmprestimo(contaCorrente);


    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../telas/Home.fxml"));

        Scene scene = new Scene(root,defaultWidth,defaultHeight);

        primaryStage.setTitle("Banco do Povo");
        primaryStage.setMinWidth(defaultWidth);
        primaryStage.setMinHeight(defaultHeight);
        primaryStage.setMaxHeight(maxHeight);
        primaryStage.setMaxWidth(maxWidth);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}