package com.banco.bancodopovo.jgi.interfaceDao;
import com.banco.bancodopovo.jgi.modelo.Emprestimo;

import java.util.ArrayList;

/**
 * Interface modelo responsável por definir os métodos que serão implementados pelas classes de acesso DAO relacionadas
 * aos tipos de empréstimo
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public interface EmprestimoDao {
    boolean createEmprestimo(Emprestimo emprestimo);
    Emprestimo getEmprestimo(String tipoEmp);
    boolean updateEmprestimo(Emprestimo emprestimo);
    boolean deleteEmprestimo(Emprestimo emprestimo);
}
