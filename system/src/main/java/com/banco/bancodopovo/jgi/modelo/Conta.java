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
    void realizarSaque(double quantiaASacar) throws SQLException, ClassNotFoundException, IOException;
    void consultarSaldo(); // RETRIEVE
    void transferir(Conta conta, double valor) throws SQLException, ClassNotFoundException, IOException;
    boolean podeTransferir(double saldo, double valor)  throws SQLException, ClassNotFoundException, IOException;
    boolean podeSacar(double saldo,double saque) throws ClassNotFoundException, IOException;
    boolean podeDepositar(double valor) throws ClassNotFoundException, IOException;
}
