package com.banco.bancodopovo.jgi.modelo;

import java.io.IOException;
import java.sql.SQLException;

public interface Conta {
    void depositar(double valorDepositado); //
    boolean realizarSaque(double quantiaASacar) throws SQLException, ClassNotFoundException, IOException;
    void consultarSaldo(); // RETRIEVE
    boolean transferir(Conta conta, double valor) throws SQLException, ClassNotFoundException, IOException;
}
