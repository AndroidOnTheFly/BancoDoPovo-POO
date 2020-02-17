package com.banco.bancodopovo.jgi.interfaceDao;

import com.banco.bancodopovo.jgi.modelo.Conta;

import java.util.ArrayList;

public interface ContaDao {
    boolean insertConta(Conta conta);
    ArrayList<Conta> getContaByCpf(String userCpf);
    boolean updateConta(Conta conta);
    boolean deleteConta(Conta conta);
}
