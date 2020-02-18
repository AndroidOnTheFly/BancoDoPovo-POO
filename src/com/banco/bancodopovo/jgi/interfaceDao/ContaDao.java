package com.banco.bancodopovo.jgi.interfaceDao;

import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.modelo.Conta;

/**
 * Interface modelo responsável por definir os métodos que serão implementados pelas classes de acesso DAO relacionadas
 * aos tipos de conta
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public interface ContaDao {
    boolean insertConta(Conta conta);
    Conta getConta(Usuario cliente);
    boolean updateConta(Conta conta, double novoSaldo);
    boolean deleteConta(Conta conta);
    Conta getContaByCpf(String cpf);

}
