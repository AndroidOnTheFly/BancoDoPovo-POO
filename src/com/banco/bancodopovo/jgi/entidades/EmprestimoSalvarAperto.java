package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.modelo.Emprestimo;

public class EmprestimoSalvarAperto implements Emprestimo {


    @Override
    public boolean pedirEmprestimo(double valor) {
        return false;
    }
}
