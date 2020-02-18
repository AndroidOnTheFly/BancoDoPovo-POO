package com.banco.bancodopovo.jgi.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Classe que seria responsável ser o controlador da cena de interface "Emprestimo"
 * Todos seus componentes e eventos são tratados aqui
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class EmprestimoController {

    @FXML
    private Label saldoLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label agenciaLabel;

    @FXML
    private Label contaLabel;

    @FXML
    private TableView<?> tableEmp;

    @FXML
    private TableColumn<?, ?> nomeEmp;

    @FXML
    private TableColumn<?, ?> valorEmp;

    @FXML
    private Button cancelButton;
}
