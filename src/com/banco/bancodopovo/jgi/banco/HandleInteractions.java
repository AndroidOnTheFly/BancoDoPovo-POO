package com.banco.bancodopovo.jgi.banco;
import com.banco.bancodopovo.jgi.controllers.PanelController;
import com.banco.bancodopovo.jgi.dao.ContaCorrenteDaoBanco;
import com.banco.bancodopovo.jgi.dao.ContaPoupancaDaoBanco;
import com.banco.bancodopovo.jgi.entidades.ContaCorrente;
import com.banco.bancodopovo.jgi.entidades.ContaPoupanca;
import com.banco.bancodopovo.jgi.entidades.Usuario;
import com.banco.bancodopovo.jgi.dao.UsuarioDaoBanco;
import com.banco.bancodopovo.jgi.modelo.Conta;
import java.util.HashMap;
import java.util.Map;

/** Classe responsável por fazer uma ponte entre as interações de usuário e a interface, permitindo o acesso e transferência
 *  de dados entre ambas.
 *
 *  @author João Pedro, Iarlyson e Gustavo
 */
public class HandleInteractions {

    /** Método responsável por validar um registro de um cliente*/
    public static boolean validateRegister(Usuario usuario){

        UsuarioDaoBanco usuarioDaoBanco = new UsuarioDaoBanco();

        if(usuarioDaoBanco.getUsuarioBy(usuario.getEmail(),"email") != null || usuarioDaoBanco.getUsuarioBy(usuario.getCpf(),"cpf") != null){
            return false;
        }
        return true;
    }

    /** Método responsável por verificar qual conta do usuário deve ser inserida no banco de dados,
     *  Podendo ser conta corrente, conta poupança ou conta mista.
     * */
    public static boolean validarTipoConta(Usuario usuario,String agencia,int tipoConta){

        boolean inserted = false;
        if(tipoConta == 1) {
            ContaCorrente conta = new ContaCorrente(usuario,agencia);
            conta.setNumConta();
            inserted = new ContaCorrenteDaoBanco().insertConta(conta);
        }else if(tipoConta == 2) {
            ContaPoupanca conta = new ContaPoupanca(usuario,agencia);
            conta.setNumConta();
            inserted = new ContaPoupancaDaoBanco().insertConta(conta);
        }else if(tipoConta == 3) {
            boolean nextInsert;
            ContaCorrente contac = new ContaCorrente(usuario,agencia);
            contac.setNumConta();
            ContaPoupanca contap = new ContaPoupanca(usuario,agencia);
            contap.setNumConta();
            inserted = new ContaCorrenteDaoBanco().insertConta(contac);
            nextInsert = new ContaPoupancaDaoBanco().insertConta(contap);
            return (inserted && nextInsert);
        }
        return inserted;
    }

    /** Método responsável por armazenar e retornar todas as contas utilizadas por um cliente */
    public static Map<String,Conta> pegarContas(Usuario cliente){

        Map<String,Conta> contaMap = new HashMap<>();
        ContaCorrente contaCorrente = new ContaCorrenteDaoBanco().getConta(cliente);
        ContaPoupanca contaPoupanca = new ContaPoupancaDaoBanco().getConta(cliente);

        contaMap.put("corrente",contaCorrente);
        contaMap.put("poupanca",contaPoupanca);

        return contaMap;

    }

    /**Método responsável retornar o tipo de conta  em runtime */
    public static int tipoDeContaUsuario(Map<String,Conta> contaMap){

        ContaCorrente cc = ((ContaCorrente)contaMap.get("corrente"));
        ContaPoupanca cp = ((ContaPoupanca)contaMap.get("poupanca"));
        if(cc.getNumContaCorrent().length() > 0 && cp.getNumContaPoupanca().length() == 0){
            return 1;
        }else if(cc.getNumContaCorrent().length() == 0 && cp.getNumContaPoupanca().length() > 0) {
            return 2;
        }
        return 3;

    }


}

