package Tests;

import org.junit.Test;
import junit.framework.TestCase;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.enumeration.Cidade;
import com.banco.bancodopovo.jgi.enumeration.TipoConta;

public class TransferenciaTest extends TestCase {

    @Test
    public void testarContaCorrenteSaldoInsuficiente() {
        // Criamos um usuário que conterá a conta corrente
        Usuario usuarioContaCorrente = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1998-07-25","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta corrente para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Setamos um saldo inicial
        contacorrente.setSaldo(1000.00);

        // Definimos um valor a ser transferido
        double valorTransferido = 980.00;

        /**
         * Fazemos a verificação da transferencia dado o contexto em que o saldo inicial da conta é R$1000
         * A operação deve retornar false, pois a taxa de juros de uma transferência de uma conta corrente
         * é de 1% em cima do valor transferido.
         */
        assertFalse(contacorrente.podeTransferir(contacorrente.getSaldo(),valorTransferido));
    }

    @Test
    public void testarContaCorrenteSaldoSuficiente() {
        // Criamos um usuário que conterá a conta corrente
        Usuario usuarioContaCorrente = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1998-07-25","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta corrente para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Depositamos um saldo inicial para a conta corrente
        contacorrente.setSaldo(1000.00);

        // Definimos um valor a ser transferido
        double valorTransferido = 900.00;

        // Fazemos a verificação da transferencia dado o contexto em que o saldo inicial da conta é de R$1500
        assertTrue(contacorrente.podeTransferir(contacorrente.getSaldo(),valorTransferido));
    }

    @Test
    public void testarSaldoContaDestino() {
        // Criamos um usuário que conterá a conta corrente
        Usuario usuarioContaCorrente = new Usuario("Joao Pedro","91835325017","abc@hotmail.com",
                "1998-07-25","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta corrente para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Depositamos um valor inicial na conta que fará a transferencia
        contacorrente.setSaldo(10000.00);

        // Criamos um usuário que receberá a transferência
        Usuario usuarioContaPoupanca = new Usuario("Pedro Arthur","49568022007","pedro@hotmail.com",
                "1997-12-18","Paraiba", Cidade.Cajazeiras, TipoConta.Poupanca,"123456");

        // Criamos uma conta do usuário que receberá a transferência
        ContaPoupanca contapoupanca = new ContaPoupanca(usuarioContaPoupanca,Cidade.Cajazeiras.getAgencia());

        // Armazenamos o saldo atual da conta do usuário de destino
        double saldoAtualContaDestino = contapoupanca.getSaldo();

        // Fazemos a transferência
        double valorTransferido = 2500.00;
        contacorrente.transferir(contapoupanca,valorTransferido);

        // Testamos se o valor do saldo da conta destino é igual ao retorno esperado.
        double retornoEsperado = saldoAtualContaDestino + valorTransferido;
        assertEquals(retornoEsperado,contapoupanca.getSaldo());
    }

    @Test
    public void testarValorContaCorrente() {
        // Criamos um usuário que conterá a conta corrente
        Usuario usuarioContaCorrente = new Usuario("Carlito","25835237011","cacaushow@bol.com.br",
                "1998-07-25","Paraiba", Cidade.JocaClaudino, TipoConta.Corrente,"123456");

        // Criamos uma conta corrente para o usuário
        ContaCorrente contacorrente = new ContaCorrente(usuarioContaCorrente,Cidade.JocaClaudino.getAgencia());

        // Depositamos um valor inicial na conta que fará a transferencia
        contacorrente.setSaldo(2578.932);

        // Criamos um usuário que receberá a transferência
        Usuario usuarioContaPoupanca = new Usuario("Sérgio Carvalho","12345678910","sergio@hotmail.com",
                "2000-05-07","Paraiba", Cidade.Cajazeiras, TipoConta.Poupanca,"123456");

        // Criamos uma conta do usuário que receberá a transferência
        ContaPoupanca contapoupanca = new ContaPoupanca(usuarioContaPoupanca,Cidade.Cajazeiras.getAgencia());

        // Realizamos a operação de transferência
        double valorTransferencia = 874.55;
        contacorrente.transferir(contapoupanca,valorTransferencia);

        // Calculo feito de acordo com o saldo atual e a taxa de juros
        double retornoEsperado =  2578.932 - (valorTransferencia + valorTransferencia * 0.1);

        // Teste
        assertEquals(retornoEsperado,contacorrente.getSaldo());
    }

    @Test
    public void testarValorContaPoupanca() {
        // Criamos um usuário que conterá a conta poupança
        Usuario usuarioContaPoupanca = new Usuario("Carlito","25835237011","cacaushow@bol.com.br",
                "1998-07-25","Paraiba", Cidade.JocaClaudino, TipoConta.Poupanca,"123456");

        // Criamos uma conta poupança para o usuário
        ContaPoupanca contaPoupanca = new ContaPoupanca(usuarioContaPoupanca,Cidade.JocaClaudino.getAgencia());

        // Depositamos um valor inicial na conta que fará a transferencia
        contaPoupanca.setSaldo(522.88);

        // Criamos um usuário que receberá a transferência
        Usuario usuarioContaCorrente = new Usuario("Sérgio Carvalho","12345678910","sergio@hotmail.com",
                "2000-05-07","Paraiba", Cidade.Cajazeiras, TipoConta.Corrente,"123456");

        // Criamos uma conta do usuário que receberá a transferência
        ContaCorrente contaCorrente = new ContaCorrente(usuarioContaCorrente,Cidade.Cajazeiras.getAgencia());

        // Realizamos a operação de transferência
        double valorTransferencia = 122.87;
        contaPoupanca.transferir(contaCorrente,valorTransferencia);

        double retornoEsperado = 400.01;
        assertEquals(retornoEsperado,contaPoupanca.getSaldo());
    }


}
