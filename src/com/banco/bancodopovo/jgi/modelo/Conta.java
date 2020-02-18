package com.banco.bancodopovo.jgi.modelo;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Interface modelo responsável por definir os métodos que serão implementados por todas as classes que representam um
 * tipo de conta
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public interface Conta {
    void depositar(double valorDepositado); //
    boolean realizarSaque(double quantiaASacar) throws SQLException, ClassNotFoundException, IOException;
    void consultarSaldo(); // RETRIEVE
    boolean transferir(Conta conta, double valor) throws SQLException, ClassNotFoundException, IOException;
}
