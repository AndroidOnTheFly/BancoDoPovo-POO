package com.banco.bancodopovo.jgi.interfaceDao;
import com.banco.bancodopovo.jgi.modelo.Emprestimo;

import java.util.ArrayList;

public interface EmprestimoDao {
    boolean createEmprestimo(Emprestimo emprestimo);
    ArrayList<Emprestimo> getEmprestimo(String tipoEmp);
    boolean updateEmprestimo(Emprestimo emprestimo);
    boolean deleteEmprestimo(Emprestimo emprestimo);
}
