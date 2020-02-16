package com.banco.bancodopovo.jgi.visao;

import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;


public class App extends Application{

    private int defaultWidth = 780;
    private int defaultHeight = 560;
    private int maxWidth = 800;
    private int maxHeight = 584;

    public static void main(String[] args) {
        launch(args);

        Usuario usuario = new Usuario("Iarlyson", "07048150440", "iarlyson.santana@outlook.com", LocalDate.now(),
                "Paraíba", Cidade.JocaClaudino, TipoConta.Corrente,"123456");

        System.out.println(usuario);
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