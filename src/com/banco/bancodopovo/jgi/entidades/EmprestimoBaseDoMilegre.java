package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.modelo.Emprestimo;

public class EmprestimoBaseDoMilegre implements Emprestimo {

    private double juros;
    private int meses;



    @Override
    public boolean pedirEmprestimo(double valor) {
        return false;
    }
}
