package com.banco.bancodopovo.jgi.banco;

import org.postgresql.util.PSQLException;
import java.sql.*;

/* Classe que faz e retorna a conexão com o banco de dados postgresql
 *
 * @author João Pedro, Iarlyson Santana e Gustavo
 */
public class ConFactory {

    private String url;
    private String usuario;
    private String senha;
    private Connection connection;

    String nomebanco = "postgres" ;//coloque o nome do banco de dados aqui
    String portaAcesso = "5432"; // coloque a porta de acesso aqui

    /** Função construtora que inicia a conexão com o banco de dados pré definido */
    public ConFactory(){

        /** os dados do postgresql devem ser referenciados aqui */


        url = "jdbc:postgresql://localhost:" + portaAcesso + "/" + nomebanco;
        usuario = "postgres";
        senha = "8975424";

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,usuario,senha);
        } catch(PSQLException ex){
            ex.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    /** Função responsável por executar um comando SQL de alteração de dados*/
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
    /** Função responsável por executar um comando SQL de seleção de dados */
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
