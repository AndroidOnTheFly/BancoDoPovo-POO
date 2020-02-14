package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UsuarioDaoBanco extends ConFactory {

    private ConFactory conFactory;

    public UsuarioDaoBanco(){
        conFactory = new ConFactory();
    }

//    public boolean salvar(Usuario usuario) throws SQLException, ClassNotFoundException, IOException {
//        getConnection();
//        PreparedStatement statement =  getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS clientes (" +
//                "nome VARCHAR(30)," +
//                "cpf VARCHAR(15) PRIMARY KEY," +
//                "numero INT);"
//        );
public boolean salvar(Usuario usuario) throws SQLException,
        ClassNotFoundException {

    Connection connection1 = conFactory.getConnection();
    PreparedStatement statement = connection1.prepareStatement("CREATE TABLE IF NOT EXISTS clientes (" +
            "nome VARCHAR(30)," +
            "cpf VARCHAR(15) PRIMARY KEY," +
            "numero INT);"

    );
    statement.executeUpdate();

    System.out.println("Aqui");
    return  true;


    //
    /*
    try (Connection connection2 = conFactory.getConnection()) {
        PreparedStatement pstmt = connection2.prepareStatement(
                "INSERT INTO usuario(nome, cpf, email, nascimento, estado, cidade, tipoConta, senha)" +
                        "VALUES (?,?,?,?,?,?,?,?");
        pstmt.setString(1, usuario.getNome());
        pstmt.setString(2, usuario.getCpf());
        pstmt.setString(3, usuario.getEmail());
        pstmt.setDate(4,
                java.sql.Date.valueOf(usuario.getNascimento()));
        pstmt.setString(5, usuario.getEstado());
        pstmt.setObject(6, usuario.getCidade());
        pstmt.setObject(5, usuario.getTipoConta());
        pstmt.setString(3, usuario.getSenha());

        return pstmt.executeUpdate() > 0;


    }

    */


}
}
