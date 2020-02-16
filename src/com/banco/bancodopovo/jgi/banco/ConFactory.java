package com.banco.bancodopovo.jgi.banco;

import java.sql.*;

public class ConFactory {

    private String url;
    private String usuario;
    private String senha;
    private Connection connection;

    public ConFactory() {
        url = "jdbc:postgresql://localhost:5432/banco_do_povo_poo";
        usuario = "postgres";
        senha = "Luta1234";

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,usuario,senha);
            System.out.println("Conexão realizada com sucesso");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int executeSQL(String sql, Boolean closeConnection){
        try{
            Statement stm = connection.createStatement() ;
            int res = stm.executeUpdate(sql);
            if(closeConnection) connection.close();
            return res;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet getQueryResult(String sql,Boolean closeConnection){
        try{
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(closeConnection) connection.close();
            return rs;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
