package com.banco.bancodopovo.jgi;

import org.junit.Test;
import junit.framework.TestCase;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;

public class SacarTest extends TestCase {
    @Test
    public void testarSaqueInvalido() {
        // Criamos um usuário que conterá uma conta
        Usuario usuarioContaCorrente = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1998-07-25","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Testamos o caso para um valor de saque inválido
        double valorSaque = 0.00;
        assertFalse(contacorrente.podeSacar(contacorrente.getSaldo(),valorSaque));
    }

    @Test
    public void testarSaqueSaldoInsuficienteContaCorrente() {
        // Criamos um usuário que conterá uma conta
        Usuario usuarioContaCorrente = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1998-07-25","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Setamos um valor de saldo inicial
        contacorrente.setSaldo(507.52);

        double valorSaque = 490.22;

        // Esperamos false porque o valor do saque acrescido da taxa de juros de 1% supera o saldo de R$507.52
        assertFalse(contacorrente.podeSacar(contacorrente.getSaldo(),valorSaque));

    }

    @Test
    public void testarSaldoContaCorrente() {
        // Criamos um usuário que conterá uma conta
        Usuario usuarioContaCorrente = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1998-07-25","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Atribuimos um saldo inicial à conta
        contacorrente.setSaldo(847.832);

        // Realizamos o saque com o valor pré definido abaixo
        double valorSaque = 150.00;
        contacorrente.realizarSaque(valorSaque);

        // Aplicados os calculos da taxa de juros de 1% sobre o saldo e realizamos o teste
        double retornoEsperado = 682.832;
        assertEquals(retornoEsperado,contacorrente.getSaldo());
    }

    @Test
    public void testarSaldoContaPoupanca() {
        // Criamos um usuário que receberá a transferência
        Usuario usuarioContaPoupanca = new Usuario("Pedro Arthur","49568022007","pedro@hotmail.com",
                "1997-12-18","Paraiba", Cidade.Cajazeiras, TipoConta.Poupanca,"123456");

        // Criamos uma conta do usuário que receberá a transferência
        ContaPoupanca contapoupanca = new ContaPoupanca(usuarioContaPoupanca,Cidade.Cajazeiras.getAgencia());

        // Atribuimos um valor inicial de saldo à conta
        contapoupanca.setSaldo(122.98);

        // Realizamos o saque
        double valorSaque = 25.00;
        contapoupanca.realizarSaque(valorSaque);

        // O retorno esperado deve ser o valor do saldo anterior menos o valor do saque.
        double retornoEsperado = 97.98;
        assertEquals(retornoEsperado,contapoupanca.getSaldo());

    }
}
