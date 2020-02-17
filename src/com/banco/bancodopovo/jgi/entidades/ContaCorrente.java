package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.modelo.Conta;
import java.util.Objects;

public class ContaCorrente implements Conta{
    //atributoss da classe
    private Usuario usuario;
    private static int ID = 1000;
    private String numContaCorrent;
    private String agencia;
    private double saldo;
    private final double juros= 1.15; //Taxa Mensal

    public ContaCorrente(Usuario usuario , String agencia) {
        this.usuario = usuario;
        this.agencia = agencia;
        ID++;
        
        this.saldo = 0;
    }

     public void setNumConta(){
        int random = (int) (Math.random() * 100);
        numContaCorrent = ID + "" + random + usuario.getCpf().substring(0,2);
    }

    public void setConta(String num){
        numContaCorrent = num;
    }

    public String getAgencia() { return agencia; };
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getSaldo() {
        return saldo;
    }
    public String getNumContaCorrent() { return numContaCorrent; }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    @Override
    public void depositar(double valorDepositado) {
        saldo +=valorDepositado;
    }

    //metodo
    @Override
    public boolean realizarSaque(double quantiaASacar)  {
        //Tem saldo na conta?
        if ((saldo-quantiaASacar*juros) >=0){
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
    public boolean transferir(Conta conta, double valor)  {
        if (valor*juros <= saldo) {
            this.realizarSaque(valor*juros);
            conta.depositar(valor*juros);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "usuario=" + usuario +
                ", numContaCorrent='" + numContaCorrent + '\'' +
                ", agencia=" + agencia +
                ", saldo=" + saldo +
                ", juros=" + juros +
                '}';
    }
}
