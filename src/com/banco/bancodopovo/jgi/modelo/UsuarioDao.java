package com.banco.bancodopovo.jgi.modelo;
import com.banco.bancodopovo.jgi.entidades.Usuario;

public interface UsuarioDao {

    boolean insertUsuario(Usuario usuario);
    boolean updateUsuario(Usuario usuario);
    boolean deleteUsuario(Usuario usuario);
    boolean getUsuario(Usuario usuario);

}
