package com.banco.bancodopovo.jgi.dao;

import com.banco.bancodopovo.jgi.banco.ConFactory;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.interfaceDao.ContaDao;
import com.banco.bancodopovo.jgi.modelo.Conta;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContaCorrenteDaoBanco implements ContaDao {

    private ConFactory connection;

    public ContaCorrenteDaoBanco(){
        connection = new ConFactory();
    }

    @Override
    public boolean insertConta(Conta conta) {
        ContaCorrente c = (ContaCorrente) conta;
        String sql = "insert into contacorrente (numconta,agencia,saldo,cpf) values (" + "'" + c.getNumContaCorrent()
                + "'," + "'" + c.getAgencia() + "'," + "'" + c.getSaldo() + "'," + "'" + c.getUsuario().getCpf() +"')";
        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;
    }

    @Override
    public ContaCorrente getConta(Usuario cliente) {
        String sql = "SELECT * from contacorrente WHERE cpf = '"+cliente.getCpf()+"'";
        ResultSet result = connection.getQueryResult(sql,true);

        String numconta = "",agencia = "";
        double saldo = 0.0;

        try{
            while(result.next()){
                System.out.println("HEY!!!");
                numconta = result.getString("numconta");
                agencia = result.getString("agencia");
                saldo = result.getDouble("saldo");
            }
            ContaCorrente conta = new ContaCorrente(cliente,agencia);
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

        ContaCorrente c = (ContaCorrente) conta;
        String sql = "update contacorrente set saldo = " + novoSaldo + " where cpf = " + "'"
                + c.getUsuario().getCpf() + "'";
        int connectionResult = connection.executeSQL(sql,true);
        if(connectionResult > 0){
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteConta(Conta conta) {
        return false;
    }
}
