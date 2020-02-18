package com.banco.bancodopovo.jgi.interfaceDao;

import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.Conta;

import java.util.ArrayList;

public interface ContaDao {
    boolean insertConta(Conta conta);
    Conta getConta(Usuario cliente);
    boolean updateConta(Conta conta, double novoSaldo);
    boolean deleteConta(Conta conta);
    Conta getContaByCpf(String cpf);

}
