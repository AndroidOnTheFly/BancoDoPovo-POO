package com.banco.bancodopovo.jgi.entidades;
import com.banco.bancodopovo.jgi.modelo.Conta;



/**
 * Classe responsável por representar uma conta poupança
 * @author joão pedro fernandes, Iarlyson Santana e Gustavo Araujo
 */
public class ContaPoupanca implements Conta {

    private Usuario usuario;
    private static int ID = 1000;
    private String numContaPoupanca;
    private String agencia;
    private double saldo;
    private final double juros= 1.15; //Taxa Mensal

    /** Construtor relacionado a classe ContaPoupanca */
    public ContaPoupanca(Usuario usuario, String agencia) {
        this.usuario = usuario;
        this.agencia = agencia;
        ID++;
        this.saldo = 0;
    }

    /** gera um numero de conta */
    public void setNumConta(){
        int random = (int) (Math.random() * 100);
        numContaPoupanca = ID + "" + random + usuario.getCpf().substring(0,2);
        System.out.println(numContaPoupanca);
    }
    /** operação de deposito */
    @Override
    public void depositar(double valorDepositado) {
        saldo +=valorDepositado*juros;
    }

    //metodo
    @Override
    public boolean realizarSaque(double quantiaASacar) {
        //Tem saldo na conta?
        if ((saldo-quantiaASacar) >=0){
            saldo-= quantiaASacar;
            return true;
        }
        //sem saldo
        return false;
    }
    /** getter e setter */
    public void setConta(String num){
        numContaPoupanca = num;
    }
    /** getter e setter */
    public void setSaldo(double s){
        saldo = s;
    }
    /** getter e setter */
    public String getAgencia() { return agencia; };
    /** getter e setter */
    public String getNumContaPoupanca(){ return numContaPoupanca; }
    /** getter e setter */
    public Usuario getUsuario() { return usuario; };
    /** getter e setter */
    public double getSaldo() { return saldo; }

    /** método responsável por consultar o saldo de uma conta */
    @Override
    public void consultarSaldo() {
        System.out.println("Saldo Atual da conta = " + saldo);
    }
    /** método responsável por realizar a operação de transferencia em uma conta poupança */
    @Override
    public boolean transferir(Conta conta, double valor) {
        if (valor <= saldo) {
            this.realizarSaque(valor);
            conta.depositar(valor);
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

