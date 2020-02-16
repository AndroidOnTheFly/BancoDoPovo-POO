package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.modelo.Emprestimo;

import java.io.IOException;
import java.sql.SQLException;

public class EmprestimoPerdicao implements Emprestimo {
    private double valor = 4000;
    private final double juros = 1.20;

    public EmprestimoPerdicao() {

    }


    @Override
    public boolean pegarEmprestimo(ContaCorrente contaCorrente) {
        contaCorrente.depositar(valor);
        return true;
    }

    @Override
    public boolean pagarPartedeEmprestimo(double valorPagamentoPacial, ContaCorrente contaCorrente) throws SQLException, IOException, ClassNotFoundException {
        if (valorPagamentoPacial <= contaCorrente.getSaldo()) {
            if (valorPagamentoPacial < valor * juros){
                valor = valor * juros;
                contaCorrente.realizarSaque(valorPagamentoPacial);
                valor = valor - valorPagamentoPacial;
                System.out.println("Valor restante do emprestimo " + valor);
                return true;
            } else {
                contaCorrente.realizarSaque(valor * juros);
                valor = valor * juros;
                valor -= valorPagamentoPacial;
                System.out.println(valor);
                return true;
            }
        }
        else{
            System.out.println("Você não tem saldo suficiente para fazer tal operação");
            return false;
        }
    }

    @Override
    public boolean pagarTotalDeEmprestimo(ContaCorrente contaCorrente) throws SQLException, IOException, ClassNotFoundException {
        if (valor*juros<=contaCorrente.getSaldo()){
            valor = valor * juros;
            contaCorrente.realizarSaque(valor);
            valor = valor - valor;
            System.out.println(valor);
            return true;
        }
        else {
            System.out.println("Saldo Insuficiente");
            return false;
        }
    }
}
