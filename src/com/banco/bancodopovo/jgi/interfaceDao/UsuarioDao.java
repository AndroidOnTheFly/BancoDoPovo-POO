package com.banco.bancodopovo.jgi.interfaceDao;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.Conta;

public interface UsuarioDao {

    boolean insertUsuario(Usuario usuario);
    boolean updateUsuario(Usuario usuario);
    boolean deleteUsuario(Usuario usuario);
    Usuario getUsuarioBy(String data, String bySearch);

}
