package com.banco.bancodopovo.jgi.entidades;

import com.banco.bancodopovo.jgi.modelo.Conta;

/**
 * Classe responsável por representar uma conta corrente
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class ContaCorrente implements Conta{
    //atributoss da classe
    private Usuario usuario;
    private static int ID = 1000;
    private String numContaCorrent;
    private String agencia;
    private double saldo;
    private final double juros = 0.1; //Taxa Mensal

    /** construtor inicializador da classe ContaCorrente */
    public ContaCorrente(Usuario usuario , String agencia) {
        this.usuario = usuario;
        this.agencia = agencia;
        ID++;
        
        this.saldo = 0;
    }

     /** gera um numéro de conta corrente */
     public void setNumConta(){
        int random = (int) (Math.random() * 100);
        numContaCorrent = ID + "" + random + usuario.getCpf().substring(0,2);
    }

    /** getter e setter */
    public void setConta(String num){
        numContaCorrent = num;
    }
    /** getter e setter */
    public String getAgencia() { return agencia; };

    /** getter e setter */
    public Usuario getUsuario() {
        return usuario;
    }
    /** getter e setter */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /** getter e setter */
    public double getSaldo() {
        return saldo;
    }
    /** getter e setter */
    public String getNumContaCorrent() { return numContaCorrent; }

    /** getter e setter */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /** realiza a operação de deposito em um objeto do tipo conta */
    @Override
    public void depositar(double valorDepositado) {
        if(this.podeDepositar(valorDepositado)) {
            saldo +=valorDepositado;
        }
    }

    /** realiza a operação de saque em um objeto do tipo conta */
    @Override
    public void realizarSaque(double quantiaASacar)  {
        //Tem saldo na conta?
        if(this.podeSacar(this.getSaldo(),quantiaASacar)) {
            saldo -= (quantiaASacar + quantiaASacar * juros);
        }
    }

    /** realiza a operação de consulta de saldo em um objeto do tipo conta*/
    @Override
    public void consultarSaldo() {
        System.out.println("Saldo Atual da conta = " + saldo);
    }
    @Override
    public boolean podeDepositar(double valor) {
        if(valor > 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean podeSacar(double saldo,double saque) {
        if(saque <= 0) {
            return false;
        }
        if (saldo - (saque + saque*juros) >= 0){
            return true;
        }
        return false;
    }

    /** método responsável por checar se uma transferência é possivel */
    @Override
    public boolean podeTransferir(double saldo, double valor) {
        if(valor <= 0) {
            return false;
        }
        if (saldo >= valor + valor * juros) {
            return true;
        }
        return false;
    }

    /** realiza a operação de transferencia em um objeto do tipo conta */
    @Override
    public void transferir(Conta conta, double valor)  {
        if(this.podeTransferir(this.getSaldo(),valor)) {
            this.realizarSaque(valor);
            conta.depositar(valor);
        }
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
