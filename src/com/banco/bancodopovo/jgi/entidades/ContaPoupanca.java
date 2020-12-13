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
    //private final double taxa = 0.1; //Taxa Mensal

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
        if(this.podeDepositar(valorDepositado)) {
            saldo += valorDepositado;
        }
    }

    //metodo
    @Override
    public void realizarSaque(double quantiaASacar) {
        //Tem saldo na conta?
        if (this.podeSacar(this.getSaldo(),quantiaASacar)) {
            saldo -= quantiaASacar;
        }
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

    @Override
    public boolean podeDepositar(double valor) {
        if(valor > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean podeSacar(double saldo, double saque) {
        if(saque <= 0) {
            return false;
        }
        if ((saldo - saque) >= 0) {
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
        if(valor <= saldo) {
            return true;
        }
        return false;
    }

    /** método responsável por realizar a operação de transferencia em uma conta poupança */
    @Override
    public void transferir(Conta conta, double valor) {
        if(this.podeTransferir(this.getSaldo(),valor)){
            this.realizarSaque(valor);
            conta.depositar(valor);
        }
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" +
                "usuario=" + usuario +
                ", numContaPoupanca='" + numContaPoupanca + '\'' +
                ", agencia='" + agencia + '\'' +
                ", saldo=" + saldo +
                //", juros=" + taxa +
                '}';
    }
}

