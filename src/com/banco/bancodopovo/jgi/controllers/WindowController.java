package com.banco.bancodopovo.jgi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowController {

    private int defaultWidth = 800;
    private int defaultHeight = 584;
    private int minWidth = 780;
    private int minHeight = 560;
    private int maxWidth = 840;
    private int maxHeight = 624;

    public void setSceneInWindow(ActionEvent event, String pathToFxml ) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource(pathToFxml));
        Scene sceneToShow = new Scene(view);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setWidth(defaultWidth);
        window.setHeight(defaultHeight);
        window.setMinWidth(minWidth);
        window.setMinHeight(minHeight);
        window.setMaxHeight(maxHeight);
        window.setMaxWidth(maxWidth);
        window.setScene(sceneToShow);
        window.show();
    }
}
