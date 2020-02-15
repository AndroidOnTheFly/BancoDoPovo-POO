package com.banco.bancodopovo.jgi.banco;

import java.sql.*;

public class ConFactory {

    private String url;
    private String usuario;
    private String senha;
    private Connection connection;

    public ConFactory() {
        url = "jdbc:postgresql://localhost:5432/POO";
        usuario = "postgres";
        senha = "8975424";

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,usuario,senha);
            System.out.println("Conexão realizada com sucesso");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int executeSQL(String sql){
        try{
            Statement stm = connection.createStatement() ;
            int res = stm.executeUpdate(sql);
            connection.close();
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet getQueryResult(String sql){
        try{
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            connection.close();
            return rs;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //public Connection getConnection() throws ClassNotFoundException, SQLException {
    //    Class.forName("org.postgresql.Driver");
    //    return DriverManager.getConnection(url, usuario, senha);
    //}

}
