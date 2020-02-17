package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.modelo.Conta;

import java.io.IOException;
import java.sql.SQLException;

public class ContaPoupanca implements Conta {

    private Usuario usuario;
    private static int ID = 1000;
    private String numContaPoupanca;
    private String agencia;
    private double saldo;
    private final double juros= 1.15; //Taxa Mensal

    public ContaPoupanca(Usuario usuario, String agencia) {
        this.usuario = usuario;
        this.agencia = agencia;
        ID++;
        this.saldo = 0;
    }

    public void setNumConta(){
        int random = (int) (Math.random() * 100);
        numContaPoupanca = ID + "" + random + usuario.getCpf().substring(0,2);
        System.out.println(numContaPoupanca);
    }
    public void setConta(String num){
            numContaPoupanca = num;
    }
    public void setSaldo(double s){
        saldo = s;
    }

    @Override
    public void depositar(double valorDepositado) {
        saldo +=valorDepositado*juros;
    }

    //metodo
    @Override
    public boolean realizarSaque(double quantiaASacar) throws SQLException, ClassNotFoundException, IOException {
        //Tem saldo na conta?
        if ((saldo-quantiaASacar) >=0){
            saldo-= quantiaASacar;
            return true;
        }
        //sem saldo
        return false;
    }

    public String getAgencia() { return agencia; };
    public String getNumContaPoupanca(){ return numContaPoupanca; }
    public Usuario getUsuario() { return usuario; };
    public double getSaldo() { return saldo; }
    @Override
    public void consultarSaldo() {
        System.out.println("Saldo Atual da conta = " + saldo);
    }



    @Override
    public boolean transferir(Conta conta, double valor) throws SQLException, ClassNotFoundException, IOException {
        if (valor <= saldo) {
            this.realizarSaque(valor);
            conta.depositar(500);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "usuario=" + usuario +
                ", numContaPoupanca='" + numContaPoupanca + '\'' +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                ", juros=" + juros +
                '}';
    }
}

