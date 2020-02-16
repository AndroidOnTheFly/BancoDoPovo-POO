package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.UsuarioDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class UsuarioDaoBanco implements UsuarioDao {


    private ConFactory connection;

    public UsuarioDaoBanco(){
       connection = new ConFactory();
    }

    @Override
    public boolean insertUsuario(Usuario usuario){

        String sql = "insert into cliente (cpf,nome,email,cidade,estado,nascimento,tipoconta,senha) values ("
                + "'" + usuario.getCpf() + "'," + "'" + usuario.getNome() + "'," + "'" + usuario.getEmail()
                + "'," + "'" + usuario.getCidade() + "'," + "'" + usuario.getEstado() + "'," +
                "'" + usuario.getNascimento() + "',"
                + "'" + usuario.getTipoConta() + "'," + "'" + usuario.getSenha() + "')";

        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;


    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean deleteUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean getUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public boolean validateRegister(Usuario usuario) {

        ResultSet result = connection.getQueryResult("select * from cliente",false);
        Boolean exists = null;
        try{
            //enquananto existir próximo cliente no bd
            while(result.next()){

                String checkedUserCpf = result.getString("cpf");
                String checkedUserEmail = result.getString("email");

                if(checkedUserCpf.equals(usuario.getCpf()) || checkedUserEmail.equals(usuario.getEmail())) {
                    exists = true;
                }
            }

            if(exists == null)
                exists = false;

        }catch(Exception e){
            e.printStackTrace();
        }

       return (exists == true ? false : true);
    }


}
