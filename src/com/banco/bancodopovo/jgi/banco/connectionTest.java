package com.banco.bancodopovo.jgi.banco;

import java.sql.ResultSet;

import static java.sql.Types.NULL;

public class connectionTest {
    public static void main(String[] args){
        ConFactory connection = new ConFactory();
        String sql = "select * from usuario";

        ResultSet result = connection.getQueryResult(sql);
        try{
            while(result.next()){
                String nome = result.getString("nome");
                String cpf = result.getString("cpf");
                String cidade = result.getString("cidade");
                String estado = result.getString("estado");
                String email = result.getString("email");
                String senha = result.getString("senha");

                System.out.println("Usuario encontrado, informações: \nNome:" +
                        " "+ nome + "\nCpf: " + cpf + "\ncidade: " +cidade+ "\nestado: " +estado );
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
