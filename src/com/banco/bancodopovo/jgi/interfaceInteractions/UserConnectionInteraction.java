package com.banco.bancodopovo.jgi.interfaceInteractions;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;

import java.sql.ResultSet;

public class UserConnectionInteraction {

    public static boolean validateRegister(Usuario usuario){

        UsuarioDaoBanco usuarioDaoBanco = new UsuarioDaoBanco();

        if(usuarioDaoBanco.getUsuarioBy(usuario.getEmail(),"email") != null || usuarioDaoBanco.getUsuarioBy(usuario.getCpf(),"cpf") != null){
            return false;
        }
        return true;
    }


}

