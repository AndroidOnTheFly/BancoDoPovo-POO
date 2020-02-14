package com.banco.bancodopovo.jgi.visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application{

    private int defaultWidth = 744;
    private int defaultHeight = 543;
    private int maxWidth = 780;
    private int maxHeight = 560;

    public static void main(String[] args) {
        launch(args);
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