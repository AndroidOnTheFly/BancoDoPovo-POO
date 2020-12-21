package com.banco.bancodopovo.jgi.interfaceDao;
import com.banco.bancodopovo.jgi.entidades.Usuario;

/**
 * Interface modelo responsável por definir os métodos que serão implementados pelas classes de acesso DAO relacionadas
 * ao objeto usuario
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public interface UsuarioDao {

    boolean insertUsuario(Usuario usuario);
    boolean updateUsuario(Usuario usuario);
    boolean deleteUsuario(Usuario usuario);
    Usuario getUsuarioBy(String data, String bySearch);

}
