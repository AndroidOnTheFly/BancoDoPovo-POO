package com.banco.bancodopovo.jgi.controllers;

import com.banco.bancodopovo.jgi.entidades.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;

public class RegisterController {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField passInput;
    @FXML
    private TextField confirmPassInput;
    @FXML
    private TextField cpfInput;
    @FXML
    private TextField estadoInput;
    @FXML
    private TextField cityInput;
    @FXML
    private DatePicker dateInput;
    @FXML
    private CheckBox ccBox;
    @FXML
    private CheckBox cpBox;
    @FXML
<<<<<<< HEAD
    void goBackToHome(MouseEvent event) throws IOException {
        Parent homeView = FXMLLoader.load(getClass().getResource("../telas/Home.fxml"));
        Scene registerScene = new Scene(homeView);
        Stage homeWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        homeWindow.setScene(registerScene);
        homeWindow.show();

        try {
            usuarioDao = new UsuarioDaoArquivo();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Falha na conexão com o arquivo",
                    "Mensagem de erro",
                    JOptionPane.ERROR_MESSAGE);
        }

        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        setContentPane(painel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        salvarButton.addActionListener(e -> {

            String email = campoEmail.getText();
            String nome = campoNome.getText();
            String dataNascimento = campoNascimento.getText().replace("/", "");

            //TODO validar os campos
            //Primeiro pelo Email


            if (email.length() > 6) {

                boolean emailValido01 = false;
                boolean emailValido02 = false;

                ArrayList<String> listEmail = new ArrayList<String>();
                listEmail.addAll((Arrays.asList("@", ".com")));

                for (int i = 0; i < (email.length() - 3); i++) {
                    System.out.println();
                    System.out.println(email.substring(i, i + 1));
                    System.out.println(email.substring(i, i + 4));
                    System.out.println();

                    if (listEmail.contains(email.substring(i, i + 1))){
                        emailValido01 = true;
                    }
                    if (listEmail.contains(email.substring(i, i + 4))){
                        emailValido02 = true;
                    }
                    System.out.println(emailValido01);
                    System.out.println(emailValido02);
                }

                if (emailValido01 && emailValido02) {
                    //Segundo pelo Nome
                    if (!nome.isEmpty()) {
                        //Verificando campo de data.
                        ArrayList<String> listdata = new ArrayList<String>();
                        listdata.addAll((Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")));
                        boolean campoValido = true;
                        for (int i = 0; i < dataNascimento.length(); i++) {
                            if (!listdata.contains(dataNascimento.substring(i, i + 1))) {
                                campoValido = false;
                            }

                        }
                        System.out.println(campoValido);
                        if (campoValido) {
                            //Validando data de nascimento

                            //Meses com 31 dias
                            ArrayList<Integer> list31 = new ArrayList<Integer>();
                            list31.addAll((Arrays.asList(1, 3, 5, 7, 8, 10, 12)));
                            //Meses com 30 dias
                            ArrayList<Integer> list30 = new ArrayList<Integer>();
                            list30.addAll((Arrays.asList(4, 6, 9, 11)));

                            int dia = Integer.parseInt(dataNascimento.substring(0, 2));
                            int mes = Integer.parseInt(dataNascimento.substring(2, 4));
                            int ano = Integer.parseInt(dataNascimento.substring(4, 8));
                            boolean dataValida = false;

                            if (ano < 2020) {
                                if (mes > 0 && mes <= 12) {
                                    if (mes == 2) {
                                        //Ano Bicesto
                                        if (ano % 4 == 0) {
                                            if (dia > 0 && dia <= 29) {
                                                dataValida = true;
                                            }
                                        } else {
                                            if (dia > 0 && dia <= 28) {
                                                dataValida = true;
                                            }
                                        }
                                    }
                                    if (list30.contains(mes)) {
                                        if (dia > 0 && dia <= 30) {
                                            dataValida = true;
                                        }
                                    }
                                    if (list31.contains(mes)) {
                                        if (dia > 0 && dia <= 31) {
                                            dataValida = true;
                                        }
                                    }
                                }
                            }

                            //Esse ano
                            if (ano == 2020) {
                                if (mes == 1) {
                                    if (dia > 0 && dia <= 31) {
                                        dataValida = true;
                                    }
                                }
                                //limite até o dia 10 de fevereiro deste ano
                                if (mes == 2) {
                                    if (dia > 0 && dia <= 10) {
                                        dataValida = true;
                                    }
                                }
                            }

                            if (dataValida) {
                                if (campoSenha1.getPassword().length != 0) {

                                    if (campoSenha2.getPassword().length != 0) {

                                        //senhas iguais
                                        if (Arrays.equals(campoSenha1.getPassword(), campoSenha2.getPassword())) {

                                            //Cadastrando usuario no banco

                                            LocalDate nascimento = LocalDate
                                                    .parse(campoNascimento.getText(), formatter);

                                            String senha = new String(campoSenha1.getPassword());

                                            Usuario usuario = new Usuario(email, nome, nascimento, senha);

                                            try {
                                                if (usuarioDao.salvar(usuario)) {
                                                    JOptionPane.showMessageDialog(this,
                                                            "Salvo com sucesso");
                                                    this.dispose();

                                                } else {
                                                    JOptionPane.showMessageDialog(this,
                                                            "Usuário já cadastrado",
                                                            "Mensagem de erro",
                                                            JOptionPane.ERROR_MESSAGE);
                                                }
                                            } catch (IOException | ClassNotFoundException ex) {
                                                JOptionPane.showMessageDialog(this,
                                                        "Falha na conexão com o arquivo",
                                                        "Mensagem de erro",
                                                        JOptionPane.ERROR_MESSAGE);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this,
                                                    "As senhas devem ser iguais");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this,
                                                "Campo repetição de senha não preenchido!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(this,
                                            "Campo senha não preenchido!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(this,
                                        "Data invalida!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Campo nascimento não preenchido!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Campo nome não preenchido!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Email invalido!");
                }
            }else{
                JOptionPane.showMessageDialog(this,
                        "Campo Email não preenchido!");
            }

        });

    }
    //temos aqui a formatação
    private void createUIComponents() {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter("##/##/####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        campoNascimento = new JFormattedTextField();
        if(formatter!= null) formatter.install(campoNascimento);
    }
}

    }




=======
    private Button signUpBt;
>>>>>>> 54cde15ae9e047672845ffa92775d3c263bef1d1

}

