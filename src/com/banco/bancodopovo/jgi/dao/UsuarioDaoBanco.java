package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.UsuarioDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UsuarioDaoBanco implements UsuarioDao {


    private ConFactory connection;

    public UsuarioDaoBanco(){
       connection = new ConFactory();
    }

    @Override
    public boolean insertUsuario(Usuario usuario,String sql){
        try{

            int connectionResult = connection.executeSQL(sql);
            if(connectionResult > 0){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
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




}
