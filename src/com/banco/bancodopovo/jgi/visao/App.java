package com.banco.bancodopovo.jgi.visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application{

    private int defaultWidth = 800;
    private int defaultHeight = 584;
    private int minWidth = 780;
    private int minHeight = 560;
    private int maxWidth = 840;
    private int maxHeight = 624;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../telas/Home.fxml"));

        Scene scene = new Scene(root,defaultWidth,defaultHeight);

        primaryStage.setTitle("Banco do Povo");
        primaryStage.setWidth(defaultWidth);
        primaryStage.setHeight(defaultHeight);
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.setMaxHeight(maxHeight);
        primaryStage.setMaxWidth(maxWidth);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}