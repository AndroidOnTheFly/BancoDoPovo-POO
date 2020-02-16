package com.banco.bancodopovo.jgi.modelo;

import com.banco.bancodopovo.jgi.entidades.ContaCorrente;

import java.io.IOException;
import java.sql.SQLException;

public interface Emprestimo {

     boolean pegarEmprestimo(ContaCorrente contaCorrente);
     boolean pagarPartedeEmprestimo(double valorPagamento, ContaCorrente contaCorrente) throws SQLException, IOException, ClassNotFoundException;
     boolean pagarTotalDeEmprestimo(ContaCorrente contaCorrente) throws SQLException, IOException, ClassNotFoundException;

}
