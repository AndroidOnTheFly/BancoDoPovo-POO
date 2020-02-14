package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class DaoUsuarioBanco {

    private conFactory = new ConFactory();

    public boolean salvar(Usuario usuario) throws SQLException, ClassNotFoundException, IOException {
        return false;
    }


    public Usuario buscarPorEmail(String email) throws SQLException, ClassNotFoundException, IOException {
        return null;
    }

    public Set<Usuario> getUsuarios() throws SQLException, ClassNotFoundException, IOException {
        return null;
    }
}

/*

        conFactory = new ConFactory();
    }

    @Override
    public boolean salvar(Usuario usuario) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conFactory.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO usuario(email, nome, nascimento, senha)" +
                    "VALUES (?,?,?,?)");
            pstmt.setString(1, usuario.getEmail());
            pstmt.setString(2, usuario.getNome());
            pstmt.setDate(3,
                    java.sql.Date.valueOf(usuario.getNascimento()));
            pstmt.setString(4, usuario.getSenha());

            return pstmt.executeUpdate() > 0;
        }

    }

    @Override
    public Usuario buscarPorEmail(String email) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conFactory.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                "SELECT * FROM usuario WHERE email = ?");
            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                String nome = rs.getString("nome");
                LocalDate nascimento = rs
                        .getDate("nascimento").toLocalDate();
                String senha = rs.getString("senha");
                return new Usuario(email, nome, nascimento, senha);
            }
            return null;
        }
    }

    @Override
    public Set<Usuario> getUsuarios() throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conFactory.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM usuario");
            Set<Usuario> usuarios = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                LocalDate nascimento = rs
                        .getDate("nascimento").toLocalDate();
                String senha = rs.getString("senha");
                usuarios.add(new Usuario(email, nome, nascimento, senha));
            }
            return usuarios;
        }
    }
*
*
* */
