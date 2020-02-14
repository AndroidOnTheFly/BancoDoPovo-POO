package com.banco.bancodopovo.jgi.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConFactory {

    private String url;
    private String usuario;
    private String senha;

    public ConFactory() {
        //Dica - Colocar essas informações em um arquivo (não público)
        url = "jdbc:postgresql://localhost:5432/banco_do_povo_poo";
        usuario = "postgres";
        senha = "123456";
    }

    public Connection getConnection() throws ClassNotFoundException,
            SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, usuario, senha);
    }

}
