package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.modelo.Emprestimo;

public class EmprestimoSerasa implements Emprestimo {
    @Override
    public boolean pedirEmprestimo(double valor) {
        return false;
    }
}
