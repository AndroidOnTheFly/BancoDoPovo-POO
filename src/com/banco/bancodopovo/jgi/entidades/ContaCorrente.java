package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.modelo.Conta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ContaCorrente implements Conta{
    //atributoss da classe
    private Usuario usuario;
    private static int ID = 1000;
    private String numContaCorrent;
    private Cidade agencia;
    private double saldo;
    private final double juros= 0.15; //Taxa Mensal

    public ContaCorrente(Usuario usuario , Cidade agencia) {
        this.usuario = usuario;
        this.agencia = agencia;
        ID++;
        setNumConta();
        this.saldo = 0;
    }

    private void setNumConta(){
        int random = (int) (Math.random() * 100);
        numContaCorrent = ID + "" + random + usuario.getCpf().substring(0,2);
        System.out.println(numContaCorrent);
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Cidade getAgencia() {
        return agencia;
    }

    public void setAgencia(Cidade agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    @Override
    public void depositar(double valorDepositado) {
        saldo +=valorDepositado;
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
        return "ContaCorrente{" +
                "usuario=" + usuario +
                ", numContaCorrent='" + numContaCorrent + '\'' +
                ", agencia=" + agencia.getAgencia() +
                ", saldo=" + saldo +
                ", juros=" + juros +
                '}';
    }
}
