package Tests;

import org.junit.Test;
import junit.framework.TestCase;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;

public class DepositoTest extends TestCase {

    @Test
    public void testeRealizaDepositoInvalido() {
        // Criamos um usuário
        Usuario usuario = new Usuario("Ryze","91835325099","zezinho@hotmail.com",
                "1872-02-11","Paraiba", Cidade.Uiraúna, TipoConta.Poupanca,"123456");
        // Criamos uma conta poupanca para o usuário
        ContaPoupanca conta = new ContaPoupanca(usuario,Cidade.Uiraúna.getAgencia());

        // Definimos um valor de depósito invalido
        double valorDeposito = 0.00;
        assertFalse(conta.podeDepositar(valorDeposito));
    }

    @Test
    public void testeRealizaDepositoValido() {
        // Criamos um usuário
        Usuario usuario = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1997-12-18","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta corrente para o usuário
        ContaCorrente conta = new ContaCorrente(usuario,Cidade.Cajazeiras.getAgencia());

        // Setamos um saldo inicial para a conta
        conta.setSaldo(255.98);

        // Criamos um valor a ser depositado para fins de testes
        double valorDepositado = 3849.25;

        // Chamamos o método depositar de uma conta corrente para efetuarmos o depósito
        conta.depositar(valorDepositado);

        // Calculamos o valor de retorno esperado
        double retornoEsperado = 4105.23; // 3849.25 + 255.98

        // Verificamos se o retorno esperado é igual ao saldo atual da conta
        assertEquals(retornoEsperado,conta.getSaldo());
    }
}
