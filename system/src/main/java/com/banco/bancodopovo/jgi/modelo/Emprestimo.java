package com.banco.bancodopovo.jgi.modelo;

import com.banco.bancodopovo.jgi.entidades.ContaCorrente;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Interface modelo responsável por definir os métodos que serão implementados por todas as classes que representam um
 * tipo de emprestimo
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public interface Emprestimo {

     boolean pegarEmprestimo(ContaCorrente contaCorrente);
     boolean pagarPartedeEmprestimo(double valorPagamento, ContaCorrente contaCorrente) throws SQLException, IOException, ClassNotFoundException;
     boolean pagarTotalDeEmprestimo(ContaCorrente contaCorrente) throws SQLException, IOException, ClassNotFoundException;

}
