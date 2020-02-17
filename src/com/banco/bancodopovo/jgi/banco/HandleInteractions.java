package com.banco.bancodopovo.jgi.banco;
import com.banco.bancodopovo.jgi.controllers.PanelController;
import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.dao.ContaPoupancaDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;
import com.banco.bancodopovo.jgi.interfaceDao.ContaDao;
import com.banco.bancodopovo.jgi.modelo.Conta;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class HandleInteractions {

    public static boolean validateRegister(Usuario usuario){

        UsuarioDaoBanco usuarioDaoBanco = new UsuarioDaoBanco();

        if(usuarioDaoBanco.getUsuarioBy(usuario.getEmail(),"email") != null || usuarioDaoBanco.getUsuarioBy(usuario.getCpf(),"cpf") != null){
            return false;
        }
        return true;
    }

    public static boolean validarTipoConta(Usuario usuario,String agencia,int tipoConta){

        boolean inserted = false;
        if(tipoConta == 1) {
            ContaCorrente conta = new ContaCorrente(usuario,agencia);
            conta.setNumConta();
            inserted = new ContaCorrenteDaoBanco().insertConta(conta);
        }else if(tipoConta == 2) {
            ContaPoupanca conta = new ContaPoupanca(usuario,agencia);
            conta.setNumConta();
            inserted = new ContaPoupancaDaoBanco().insertConta(conta);
        }else if(tipoConta == 3) {
            boolean nextInsert;
            ContaCorrente contac = new ContaCorrente(usuario,agencia);
            contac.setNumConta();
            ContaPoupanca contap = new ContaPoupanca(usuario,agencia);
            contap.setNumConta();
            inserted = new ContaCorrenteDaoBanco().insertConta(contac);
            nextInsert = new ContaPoupancaDaoBanco().insertConta(contap);
            return (inserted && nextInsert);
        }
        return inserted;
    }

    public static Map<String,Conta> pegarContas(Usuario cliente){

        Map<String,Conta> contaMap = new HashMap<>();
        ContaCorrente contaCorrente = new ContaCorrenteDaoBanco().getConta(cliente);
        ContaPoupanca contaPoupanca = new ContaPoupancaDaoBanco().getConta(cliente);

        contaMap.put("corrente",contaCorrente);
        contaMap.put("poupanca",contaPoupanca);

        return contaMap;

    }

    public static int tipoDeContaUsuario(Map<String,Conta> contaMap){

        ContaCorrente cc = ((ContaCorrente)contaMap.get("corrente"));
        ContaPoupanca cp = ((ContaPoupanca)contaMap.get("poupanca"));
        if(cc.getNumContaCorrent().length() > 0 && cp.getNumContaPoupanca().length() == 0){
            return 1;
        }else if(cc.getNumContaCorrent().length() == 0 && cp.getNumContaPoupanca().length() > 0) {
            return 2;
        }
        return 3;

    }

    public static Conta pegarContaPeloSeuTipo(int t){
        if(t == 1)
            return PanelController.contaCorrente;
        else if(t == 2)
            return PanelController.contaPoupanca;

        return PanelController.contaCorrente;
    }

}

