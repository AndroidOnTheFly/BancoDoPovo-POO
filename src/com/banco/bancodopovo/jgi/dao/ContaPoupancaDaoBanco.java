package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.interfaceDao.ContaDao;
import com.banco.bancodopovo.jgi.modelo.Conta;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ContaPoupancaDaoBanco implements ContaDao {

    private ConFactory connection;

    public ContaPoupancaDaoBanco(){
        connection = new ConFactory();
    }

    @Override
    public boolean insertConta(Conta conta) {
        ContaPoupanca c = (ContaPoupanca) conta;

        String sql = "insert into contapoupanca (numconta,agencia,saldo,cpf) values (" + "'" + c.getNumContaPoupanca()
                + "'," + "'" + c.getAgencia() + "'," + "'" + c.getSaldo() + "'," + "'" + c.getUsuario().getCpf() +"')";
        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;
    }

    @Override
    public ContaPoupanca getConta(Usuario cliente) {
        String sql = "SELECT * from contapoupanca WHERE cpf = '"+cliente.getCpf()+"'";
        ResultSet result = connection.getQueryResult(sql,true);

        String numconta = "",agencia = "";
        double saldo = 0.0;

        try{
            while(result.next()){
                numconta = result.getString("numconta");
                agencia = result.getString("agencia");
                saldo = result.getDouble("saldo");
            }
            ContaPoupanca conta = new ContaPoupanca(cliente,agencia);
            conta.setConta(numconta);
            conta.setSaldo(saldo);

            return conta;

        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateConta(Conta conta,double novoSaldo) {

        ContaPoupanca c = (ContaPoupanca) conta;
        String sql = "update contapoupanca set saldo = " + novoSaldo + " where cpf = " + "'"
                + c.getUsuario().getCpf() + "'";
        int connectionResult = connection.executeSQL(sql,false);
        if(connectionResult > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteConta(Conta conta) {

        String sql = "DELETE FROM contapoupanca WHERE numconta = '" + ((ContaPoupanca)conta).getNumContaPoupanca() + "'";
        int result = connection.executeSQL(sql,true);
        if(result > 0)
            return true;
        return false;
    }

    @Override
    public Conta getContaByCpf(String cpf) {
        Usuario transferUser = new UsuarioDaoBanco().getUsuarioBy(cpf,"cpf");
        if(transferUser == null)
            return null;
        ContaPoupanca contaDestino = getConta(transferUser);
        return (contaDestino == null ? null : contaDestino);
    }
}
